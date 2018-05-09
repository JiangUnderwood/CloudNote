package com.pwc.fkp.note.controller;

import com.pwc.fkp.note.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author : Frank Jiang
 * @Date : 09/05/2018 10:02 AM
 */
@Controller
@RequestMapping("/note")
public class NoteController {

    private static Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;


}
