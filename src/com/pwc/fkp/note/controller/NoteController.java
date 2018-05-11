package com.pwc.fkp.note.controller;

import com.pwc.fkp.note.bean.Note;
import com.pwc.fkp.note.bean.NoteBook;
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
import java.util.List;

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
     * @param request
     * @return
     */
    @RequestMapping("/getAllNoteBook")
    public ModelAndView getAllNoteBooks(HttpServletRequest request) {
        ModelAndView mv = null;
        String userName = null;
        try {
            //从session中获取用户名
            userName = (String) request.getSession().getAttribute(Constants.USER_INFO);
            //查询当前用户名下的所有笔记
            List<NoteBook> noteBookList = noteService.getAllNoteBooks(userName);
            //装填返回值
            ModelMap map = new ModelMap();
            map.put("allNoteBook", noteBookList);
           /* map.put("recycleBtRowKey", userName + Constants.RECYCLE);
            map.put("starBtRowKey", );
            map.put("activityBtRowKey", );*/
            mv = new ModelAndView(new MappingJacksonJsonView(), map);
        } catch (Exception e) {
            logger.error("用户 ： " + userName + "获取所有笔记本异常 | 方法 ：getAllNoteBooks " +
                    "| 参数userName ：" + userName, e);
            e.printStackTrace();
        }

        return mv;
    }


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

    /**
     * 删除笔记本
     *
     * @param request
     * @param noteBookName 笔记本名称
     * @param rowKey       hbase中该笔记本的行键
     * @return
     */
    @RequestMapping("/deleteNoteBook")
    public ModelAndView deleteNoteBook(HttpServletRequest request, String noteBookName, String rowKey) {
        ModelAndView mv = null;
        //分割rowKey，获取userName和createTime
        String[] splits = rowKey.split("\\" + Constants.ROWKEY_SEPARATOR);
        try {
            //获取当前笔记本下的笔记信息
            List<Note> notes = noteService.getNoteListByNoteBook(rowKey);
            ModelMap map = new ModelMap();

            if (notes != null && notes.size() > 0) {
                //如果不为空，不允许删除笔记本
                map.put("success", false);
                map.put("message", "请先删除名下所有笔记。");
            } else {
                //删除指定用户的笔记本
                boolean delSuccess = noteService.deleteNoteBook(noteBookName, splits[0], splits[1], 0);
                map.put("success", delSuccess);
            }
            mv = new ModelAndView(new MappingJacksonJsonView(), map);
        } catch (Exception e) {
            String userName = (String) request.getSession().getAttribute(Constants.USER_INFO);
            logger.debug("" + userName + "添加笔记本异常 | 方法：deleteNoteBook " +
                    "| 参数：noteBookName : " + noteBookName + "; rowKey : " + rowKey, e);
            e.printStackTrace();
        }

        return mv;
    }
}
