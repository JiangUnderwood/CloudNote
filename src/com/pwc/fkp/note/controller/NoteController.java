package com.pwc.fkp.note.controller;

import com.pwc.fkp.note.service.NoteService;
import com.pwc.fkp.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 添加笔记本
     *
     * @param request
     * @param noteBookName : 笔记本名字
     * @return
     */
    @RequestMapping("/addNoteBook")
    public ModelAndView addNoteBook(HttpServletRequest request, String noteBookName) {
        ModelAndView mv = null;
        //从session中获取用户信息
        String userName = (String) request.getSession().getAttribute(Constants.USER_INFO);
        try {
            //创建时间戳
            Long createTime = System.currentTimeMillis();
            //保存笔记本
            boolean saveFlag = noteService.addNoteBook(noteBookName, userName, createTime.toString(), 0);
            ModelMap map = new ModelMap();
            if (saveFlag) {
                //封装rowKey信息返回前台
                map.put("success", true);
                map.put("resource", userName + Constants.ROWKEY_SEPARATOR + createTime);
            } else {
                map.put("success", false);
            }
            mv = new ModelAndView(new MappingJacksonJsonView(), map);
        } catch (Exception e) {
            logger.error("" + userName + "添加笔记本异常 | 方法：addNoteBook | 参数：noteBookName : " + noteBookName, e);
            e.printStackTrace();
        }
        return mv;
    }
}
