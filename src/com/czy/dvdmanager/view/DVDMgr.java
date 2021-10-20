package com.czy.dvdmanager.view;

import com.czy.dvdmanager.entity.DVD;
import com.czy.dvdmanager.entity.Log;
import com.czy.dvdmanager.entity.User;
import com.czy.dvdmanager.enums.State;
import com.czy.dvdmanager.service.DVDService;
import com.czy.dvdmanager.service.LogService;
import com.czy.dvdmanager.service.UserService;
import com.czy.dvdmanager.util.DBHelper;
import com.czy.dvdmanager.util.MyUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DVDMgr {

    public static int userId = 1;

    private final Scanner scanner = new Scanner(System.in);
    private final DVDService dvdService = (DVDService) MyUtil.getInstance("DVD_SERVICE");
    private final UserService userService = (UserService) MyUtil.getInstance("USER_SERVICE");
    private final LogService logService = (LogService) MyUtil.getInstance("LOG_SERVICE");

    //初始化界面
    public String init(){
        System.out.println("-------------------------");
        System.out.println("------  1:登录     ------");
        System.out.println("------  2:注册     ------");
        System.out.println("------  0:退出     ------");
        System.out.println("-------------------------");
        System.out.print("请输入序号:");
        return scanner.nextLine();
    }

    public void initChoose(){
        while (true){
            String choose = init();
            if ("1".equals(choose)){
                loginView();
            }else if ("2".equals(choose)){
                registerView();
            }else if("0".equals(choose)){
                return;
            }else {
                System.err.println("请按要求输入");
            }
        }
    }
    //注册视图
    public void registerView(){
        Connection conn = DBHelper.getConnection();
        User user = new User();
        System.out.print("请输入姓名:");
        user.setUserName(scanner.nextLine());
        System.out.print("请输入密码:");
        user.setPassword(scanner.nextLine());

        try {
            userService.register(user,conn);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }finally {
            DBHelper.closeConnection(conn);
        }
    }
    //登录视图
    public void loginView(){
        Connection conn = DBHelper.getConnection();
        System.out.print("请输入姓名:");
        String userName = scanner.nextLine();
        System.out.print("请输入密码:");
        String password = scanner.nextLine();

        try {
            userId = userService.login(userName,password,conn).getId();
            welcomeChoose();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }finally {
            DBHelper.closeConnection(conn);
        }
    }
    //首页
    public String welcome(){
        System.out.println("-----------首页----------");
        System.out.println("------  1:日志管理  ------");
        System.out.println("------  2:个人信息  ------");
        System.out.println("------  3:DVD管理  ------");
        System.out.println("------  4:借阅管理  ------");
        System.out.println("------  0:退出     ------");
        System.out.println("-------------------------");
        System.out.print("请输入序号:");
        return scanner.nextLine();
    }
    public void welcomeChoose(){
        while (true){
            String choose = welcome();
            if ("1".equals(choose)){
                logMgrViewChoose();
            }else if ("2".equals(choose)){
                userInfoView();
            }else if("3".equals(choose)){
                DvdMgrViewChoose();
            }else if ("4".equals(choose)){
                returnView();
            }else {
                return;
            }
        }
    }

    public void userInfoView(){
        User user = userService.getById(userId);
        System.out.println("姓名:"+user.getUserName());
        System.out.println("密码:"+user.getPassword());
        System.out.println("注册时间:"+user.getCreatTime());
        System.out.println("按任意键返回");
        scanner.nextLine();

    }

    //日志首页--------------
    public String logMgrView(){
        List<Log> logList = logService.listAllSortByTime();
        showLogList(logList);
        System.out.println("-----------------------------------------");
        System.out.println("1:通过DVDId查询日志\t2:通过用户id查询日志");
        System.out.println("3:通过操作日期查询日志\t4:通过操作信息查询日志\t0:退出");
        System.out.print("--请输入序号:");
        return scanner.nextLine();
    }

    public void queryLogByDvdId(){
        System.out.print("--请输入DVDId:");
        try {
            int dvdId = scanner.nextInt();
            scanner.nextLine();
            showLogList(logService.listByDvdId(dvdId));
            System.out.println("-----------------------------------------");
            System.out.println("--按任意键返回");
            scanner.nextLine();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    public void queryLogByUserId(){
        System.out.print("--请输入用户Id:");
        try {
            int userId = scanner.nextInt();
            scanner.nextLine();
            showLogList(logService.listByUserId(userId));
            System.out.println("-----------------------------------------");
            System.out.println("--按任意键返回");
            scanner.nextLine();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    public void queryLogByTime(){
        System.out.print("--请输入日期(例:2021-2-1):");
        try {
            String time = scanner.nextLine();
            long timeLong = MyUtil.getDate(time);
            showLogList(logService.ListInOneDay(timeLong));
            System.out.println("-----------------------------------------");
            System.out.println("--按任意键返回");
            scanner.nextLine();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    public void queryLogByInfo(){
        System.out.print("--请输入操作信息:");
        try {
            String info = scanner.nextLine();
            showLogList(logService.listByInfoLike(info));
            System.out.println("-----------------------------------------");
            System.out.println("--按任意键返回");
            scanner.nextLine();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void logMgrViewChoose(){
        while (true) {
            String choose = logMgrView();
            if ("1".equals(choose)){
                queryLogByDvdId();
            }else if ("2".equals(choose)){
                queryLogByUserId();
            }else if ("3".equals(choose)){
                queryLogByTime();
            }else if ("4".equals(choose)){
                queryLogByInfo();
            }else {
                return;
            }
        }
    }
    private void showLogList(List<Log> logList){
        System.out.println("----------日志查询界面------------");
        System.out.println("用户名ID+名\t\tDVD ID+名\t\t\t\t操作信息\t\t操作时间");
        for (Log log : logList) {
            System.out.printf("%3s+", log.getDvdId());
            System.out.printf("%-13s", userService.getById(log.getUserId()).getUserName());
            System.out.printf("%5s+", log.getDvdId());
            DVD dvd = dvdService.getById(log.getDvdId());
            System.out.printf("%-18s", dvd == null ? "*" : dvd.getName());
            System.out.printf("%-8s\t", log.getOperateInfo());
            System.out.print(MyUtil.getDate(log.getOperateTime()));
            System.out.println();
        }
    }
    //日志结束-----------------

    //借阅管理--------------------------------

    public void returnView(){
        while (true){
            List<DVD> dvdList = dvdService.listUserLendLog(userId);
            showDVDList("当前已借阅",dvdList);
            System.out.println("1:归还\t0:退出");
            System.out.print("请输入序号:");
            String choose = scanner.nextLine();
            if ("1".equals(choose)){
                returnDVDView();
            }else {
                return;
            }
        }
    }

    public void returnDVDView(){
        System.out.print("请输入DVDId:");
        try {
            int dvdId = scanner.nextInt();
            scanner.nextLine();
            dvdService.returnDVD(dvdId);
            System.out.println("归还成功");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    //借阅结束-----------------------------------

    //dvd管理-------------------------------
    public String DvdMgrView(){
        showDVDList("首页",dvdService.listAll());
        System.out.println("--------------------------------------------");
        System.out.println("1:添加DVD\t2:修改DVD\t3:删除DVD\t4:通过借阅数排序");
        System.out.println("5:通过DVD名查询\t6:查询已借出\t7:查询未借出");
        System.out.println("8:查询已删除\t9:借阅\t0:返回");
        System.out.print("--请输入序号:");
        return scanner.nextLine();
    }

    public void addDvdView(){
        Connection conn = DBHelper.getConnection();
        DVD dvd = new DVD();
        try {
            System.out.print("请输入DVD名称:");
            dvd.setName(scanner.nextLine());
            System.out.print("请输入DVD价格:");
            dvd.setPrice(scanner.nextDouble());
            scanner.nextLine();
            dvdService.add(dvd,conn);
        }catch (Exception e){
//            e.printStackTrace();
            System.err.println(e.getMessage());
        }finally {
            DBHelper.closeConnection(conn);
        }
    }
    public void updateDvdView(){
        Connection conn = DBHelper.getConnection();
        DVD dvd = null;
        try {
            System.out.print("请输入修改的DVDId:");
            dvd = dvdService.getById(scanner.nextInt());
            scanner.nextLine();
            if (dvd == null){
                System.err.println("该DVD不存在");
            }else {
                updateDvdView(dvd);
                dvdService.updateById(dvd,conn);
            }

        }catch (Exception e){
//            e.printStackTrace();
            System.err.println(e.getMessage());
        }finally {
            DBHelper.closeConnection(conn);
        }
    }
    public void updateDvdView(DVD dvd){
        while (true){
            List<DVD> list = new ArrayList<>();
            list.add(dvd);
            showDVDList("修改界面",list);
            System.out.println("1:DVD名称\t2:DVD价格\t0:返回");
            System.out.print("请输入要修改的序号:");
            String choose = scanner.nextLine();
            if ("1".equals(choose)){
                System.out.print("请输入新的DVD名称:");
                dvd.setName(scanner.nextLine());
            }else if ("2".equals(choose)){
                System.out.print("请输入新的DVD价格:");
                dvd.setPrice(scanner.nextDouble());
                scanner.nextLine();
            }else {
                return;
            }
        }
    }
    public void delDvdView(){
        Connection conn = DBHelper.getConnection();
        try {
            System.out.print("请输入要删除的DVDId:");
            int dvdId = scanner.nextInt();
            scanner.nextLine();
            DVD dvd = dvdService.getById(dvdId);
            if (dvd == null){
                System.err.println("该DVD不存在");
            }else {
                dvdService.delById(dvdId,conn);
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally {
            DBHelper.closeConnection(conn);
        }

    }

    public void queryDVDSortByLendCount(){
        while (true){
            showDVDList("按借阅数查询",dvdService.listSortByLendCount());
            if (lendDvdView()){
                return;
            }
        }
    }
    public void queryDVDByNameLike(){
        System.out.print("请输入图书名:");
        String name = scanner.nextLine();
        while (true){
            showDVDList("按名称查询",dvdService.listByNameLike(name));
            if (lendDvdView()){
                return;
            }
        }
    }
    public void queryHasLendDVD(){
        showDVDList("已借出DVD",dvdService.listLend());
        System.out.println("按任意健退出");
        scanner.nextLine();
    }
    public void queryNotLendDVD(){
        while (true){
            showDVDList("查询未借出",dvdService.listNotLend());
            if (lendDvdView()){
                return;
            }
        }
    }
    public void queryIsDel(){
        showDVDList("已删除DVD",dvdService.listHasDel());
        System.out.println("按任意健退出");
        scanner.nextLine();
    }

    //借阅DVD
    public boolean lendDvdView(){
        boolean flag = false;
        System.out.println("1:借阅\t0:返回");
        System.out.print("请输入你的选择:");
        String choose = scanner.nextLine();
        if ("1".equals(choose)){
            try {
                System.out.print("请输入借阅DVD的Id:");
                dvdService.lendDVD(scanner.nextInt());
                scanner.nextLine();
                flag = true;
                System.out.println("借阅成功");
            }catch (Exception e){
                System.err.println(e.getMessage());
            }

        }else {
            flag = true;
        }
        return flag;
    }


    public void DvdMgrViewChoose(){
        while (true) {
            String choose = DvdMgrView();
            if ("1".equals(choose)){
                addDvdView();
            }else if ("2".equals(choose)){
                updateDvdView();
            }else if ("3".equals(choose)){
                delDvdView();
            }else if ("4".equals(choose)){
                queryDVDSortByLendCount();
            }else if ("5".equals(choose)){
                queryDVDByNameLike();
            }else if ("6".equals(choose)){
                queryHasLendDVD();
            }else if ("7".equals(choose)){
                queryNotLendDVD();
            }else if ("8".equals(choose)){
                queryIsDel();
            }else if ("9".equals(choose)){
                lendDvdView();
            }else {
                return;
            }
        }
    }

    private void showDVDList(String message,List<DVD> dvdList){
        System.out.println("----------"+message+"------------");
        System.out.println("ID\t名称\t\t\t\t借阅数\t\t价格\t\t状态");
        for (DVD dvd : dvdList) {
            System.out.printf("%-3s\t", dvd.getId());
            System.out.printf("%-15s\t", dvd.getName());
            System.out.printf("%-5d\t", dvd.getLenCount());
            System.out.printf("%-5.2f\t", dvd.getPrice());
            System.out.printf("%-5s", State.getMessage(dvd.getStatus()));
            System.out.println();
        }
    }
    //dvd结束------------------------------------


    public static void main(String[] args) {
        DVDMgr dvdMgr = new DVDMgr();
//        System.out.println(dvdMgr.dvdService);
//        dvdMgr.LogMgrViewChoose();
//        dvdMgr.showDVDList("查询",dvdMgr.dvdService.listAll());
//        dvdMgr.showLogList(dvdMgr.logService.listAllSortByTime());
//        dvdMgr.DvdMgrViewChoose();
        dvdMgr.initChoose();
    }
}
