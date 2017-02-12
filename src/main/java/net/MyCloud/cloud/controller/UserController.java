package net.MyCloud.cloud.controller;

import net.MyCloud.cloud.exceptions.FileErrorException;
import net.MyCloud.cloud.model.Data;
import net.MyCloud.cloud.model.User;
import net.MyCloud.cloud.service.DataService;
import net.MyCloud.cloud.service.SecurityService;
import net.MyCloud.cloud.service.UserService;
import net.MyCloud.cloud.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Array;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    List<String> list = new ArrayList<String>();

    @Autowired
    private DataService dataService;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    //security
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        File userDir = new File(System.getProperty("user.dir") + File.separator + userForm.getUsername());
        userDir.mkdir();
        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "login";
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String welcome(Model model, @ModelAttribute("type") String type) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userDataList", dataService.userDataList(userService.findByUsername(name).getId(), type));
        model.addAttribute("DownloadList", list);
        model.addAttribute("path", System.getProperty("user.dir") + File.separator + SecurityContextHolder.getContext().getAuthentication().getName() + File.separator);
        return "index";
    }


    @RequestMapping(value = "/index_adm", method = RequestMethod.GET)
    public String admin(Model model) {
        return "index_adm";
    }

//work with files

    @RequestMapping(value = "/add_file", method = RequestMethod.POST)
    public String onAddFile(Model model, @RequestParam("file") MultipartFile file, Principal principal) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (file.isEmpty())
            throw new FileErrorException();
        File dir = new File(System.getProperty("user.dir") + File.separator + name + File.separator + file.getOriginalFilename());

        try {
            byte[] arr = file.getBytes();
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dir));
            out.write(arr);
            out.flush();
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //1048576 bytes in 1 MB
        dataService.addData(new Data(file.getOriginalFilename(), userService.findByUsername(name).getId(), getFileExtention(file.getOriginalFilename()), file.getSize() / 1048576));
        return "redirect:/index";
    }


    @RequestMapping(value = "/delete_file", method = RequestMethod.POST)
    public String onDeleteFile(@RequestParam(value = "toSelect[]") long[] toSelect) {
        if (toSelect.length == 0) {
            throw new FileErrorException();
        }
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        for (long a: toSelect) {
            File dir = new File(System.getProperty("user.dir") + File.separator + name + File.separator + dataService.findByDataid(a).getName());
            dir.delete();
        }
        dataService.deleteData(toSelect);
        return "redirect:/index";
    }

    @RequestMapping(value = "/download_file", method = RequestMethod.POST)
    public ResponseEntity<Void> onDownloadFile(@RequestParam(value = "toSelect[]", required = false) long[] toSelect, Model model) {
        if (toSelect.length == 0) {
            throw new FileErrorException();
        }
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        list.clear();
        for (long a: toSelect) {
            list.add(dataService.findByDataid(a).getName());
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String onDeleteFile(@ModelAttribute("search") String pattern, Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userDataList", dataService.searchData(pattern));
        return "index";
    }

    private String getFileExtention(String filename) {
        int dotPos = filename.lastIndexOf(".") + 1;
        return filename.substring(dotPos);
    }

}
