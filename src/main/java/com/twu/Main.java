package com.twu;

import com.twu.entity.Admin;
import com.twu.entity.HotSearch;
import com.twu.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<User> userList = new ArrayList<>();
        Admin admin = new Admin("admin", "password");
        boolean operation = true;
        while (operation) {
            System.out.println("欢迎来到热搜排行榜，你是？");
            System.out.println("1.用户");
            System.out.println("2.管理员");
            System.out.println("3.退出");
            Scanner scanner = new Scanner(System.in);
            int userSelect = scanner.nextInt();
            switch (userSelect) {
                case 1 :
                    System.out.println("请输入您的昵称");
                    String username = scanner.next();
                    List<User> users = userList.stream().filter(user -> user.name.equalsIgnoreCase(username)).collect(Collectors.toList());
                    User user;
                    if (!users.isEmpty()) {
                        user = users.get(0);
                    }else {
                        user = new User(username);
                        userList.add(user);
                    }
                    boolean userOperationBool = true;
                    while (userOperationBool) {
                        System.out.println("你好，" + username + "，你可以：");
                        System.out.println("1.查看热搜排行榜");
                        System.out.println("2.给热搜事件投票");
                        System.out.println("3.购买热搜");
                        System.out.println("4.添加热搜");
                        System.out.println("5.退出");
                        int userOperate = scanner.nextInt();
                        switch (userOperate) {
                            case 1 :
                                user.getHotSearchRankings();
                                break;
                            case 2 :
                                System.out.println("请输入你要投票的名称：");
                                String hotSearchNameByVote = scanner.next();
                                System.out.println("请输入你要投票的热搜票数：" + "(你目前还有：" + user.votes + ")票");
                                int votes = scanner.nextInt();
                                user.voting(hotSearchNameByVote, votes);
                                break;
                            case 3 :
                                System.out.println("请输入你要购买的热搜名称：");
                                String hotSearchNameByPurchase = scanner.next();
                                System.out.println("请输入你要购买的热搜排名：");
                                int purchaseRank = scanner.nextInt();
                                System.out.println("请输入你要购买的热搜金额：");
                                Integer purchasePrice = scanner.nextInt();
                                user.purchase(hotSearchNameByPurchase, purchaseRank, purchasePrice);
                                break;
                            case 4 :
                                System.out.println("请输入你要添加的热搜事件的名字：");
                                String hotSearchName = scanner.next();
                                user.addHotSearch(new HotSearch(hotSearchName));
                                break;
                            case 5 :
                                userOperationBool = false;
                                System.out.println("退出账号成功！");
                                break;
                            default:
                                System.out.println("未知指令，请重新输入！");
                                break;
                        }
                    }
                    break;
                case 2 :
                    System.out.println("请输入您的昵称：");
                    String adminName = scanner.next();
                    System.out.println("请输入您的密码：");
                    String password = scanner.next();
                    if (!admin.name.equals(adminName) || !admin.password.equals(password)) {
                        System.out.println("账号或密码错误，请重新输入");
                        break;
                    }
                    boolean adminOperateBool = true;
                    while (adminOperateBool) {
                        System.out.println("你好，" + admin.name + "，你可以：");
                        System.out.println("1.查看热搜排行榜");
                        System.out.println("2.添加热搜");
                        System.out.println("3.添加超级热搜");
                        System.out.println("4.退出");
                        int adminOperate = scanner.nextInt();
                        switch (adminOperate) {
                            case 1 :
                                admin.getHotSearchRankings();
                                break;
                            case 2 :
                                System.out.println("请输入你要添加的热搜事件的名字：");
                                String hotSearchName = scanner.next();
                                admin.addHotSearch(new HotSearch(hotSearchName));
                                break;
                            case 3 :
                                System.out.println("请输入你要添加的超级热搜事件的名字：");
                                String superHotSearchName = scanner.next();
                                admin.addSuperHotSearch(new HotSearch(superHotSearchName));
                                break;
                            case 4 :
                                adminOperateBool = false;
                                System.out.println("退出账号成功！");
                                break;
                            default:
                                System.out.println("未知指令，请重新输入！");
                                break;
                        }
                    }
                    break;

                case 3 :
                    operation = false;
                    System.out.println("退出系统成功，程序运行结束！");
                    break;
                default :
                    System.out.println("未知指令，请重新输入！");
                    break;
            }

        }

    }
}
