/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 8.0.13 : Database - news_manage
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`news_manage` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `news_manage`;

/*Table structure for table `my_comments` */

DROP TABLE IF EXISTS `my_comments`;

CREATE TABLE `my_comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `news_id` bigint(20) DEFAULT NULL,
  `users_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKameb5q32m7ktugohrnukvw20e` (`news_id`),
  KEY `FK28jq5fjfwro83uc9wxp58jbkm` (`users_id`),
  CONSTRAINT `FK28jq5fjfwro83uc9wxp58jbkm` FOREIGN KEY (`users_id`) REFERENCES `my_users` (`id`),
  CONSTRAINT `FKameb5q32m7ktugohrnukvw20e` FOREIGN KEY (`news_id`) REFERENCES `my_news` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `my_comments` */

insert  into `my_comments`(`id`,`content`,`createdate`,`news_id`,`users_id`) values (11,'有没有英文版的？','2018-12-23 03:49:46',14,16),(12,'怎么说呢？看不懂','2018-07-09 03:49:57',13,18),(14,'他的评论内容','2019-01-15 12:03:20',14,16),(16,'1784383478','2019-01-03 03:09:48',13,19),(17,'现在','2019-01-03 03:22:18',14,19),(18,'没想到呀','2019-01-03 03:29:14',14,16),(19,'这文案不行','2019-01-06 00:25:23',14,22),(20,'都是骗人的','2019-01-06 05:12:45',22,16),(21,'我爱共产党','2019-01-06 07:58:01',15,19),(22,'我要回家了~~~好开心','2019-01-06 07:58:16',16,19),(23,'现在的明星是真的过分，耍粉丝跟不要钱似的','2019-01-06 07:58:43',17,19),(24,'要不你说我们为啥还读书呢？？？','2019-01-06 07:59:01',22,19),(25,'这电影我看过','2019-01-06 08:03:30',23,19),(26,'nininni','2019-01-06 08:43:50',13,19);

/*Table structure for table `my_news` */

DROP TABLE IF EXISTS `my_news`;

CREATE TABLE `my_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `my_news` */

insert  into `my_news`(`id`,`content`,`createdate`,`title`) values (13,'和讯银行消息 近日，广发银行南京分行及下属水西门支行曝出窝案，通过“打擦边球”“形式合规”等方式，4名员工为水西门支行行长周群前夫违规放贷，导致贷款本金总计7600余万元未收回。','2018-12-03 02:11:47','广发银行曝南京窝案：为行长前夫违规放贷7688万'),(14,'【陈道明当选中国电影家协会主席】在刚刚举行的中国影协十届一次理事会上，陈道明当选中国影协主席，于冬、尹力、尹红、成龙、任仲伦、苏小卫、吴京 、张宏、张涵予、黄渤、喇培康当选副主席。','2018-12-16 02:27:39','陈道明当选中国电影家协会主席 成龙吴京黄渤当选副主席'),(15,'青藏高原，群山巍巍。为响应国家三江源生态保护政策，128户牧民离开祖辈放牧的草场，告别了游牧生活，搬迁到格尔木市区西南郊的长江源村定居。从草原的利用者转变成生态的保护者，当上了草场管护员，进城打工创业有了稳定收入，牧民们日子过得一天比一天红火。如今恢复了草木繁茂，正在休养生息的三江源，每年向下游输送水量已经超过600亿立方米。长江是一条习近平总书记深深牵挂的河。总书记曾多次强调：“推动长江经济带发展必须从中华民族长远利益考虑。”举旗定向，饮水思源，要使长江这条母亲河永葆生机活力，顺势而为方能行稳致远。','2018-12-30 08:28:57','党中央关心长江经济带发展纪实'),(16,'北京头条客户端1月5日消息，1月21日，2019年春运大幕即将开启。根据客流情况，铁路部门会临时增开多趟列车运送旅客。北青报记者了解到，根据春运客流特点，节前和节后，这些增开列车满载旅客抵达终点后由于返程旅客少，常常出现“回空”返回的情况。针对这一情况，近几年来，铁路部门为鼓励旅客错峰、返向过年推出了回空列车部分席位打折的优惠措施。','2019-01-01 08:32:09','春运火车票也打折，部分“回空”增开列车最高可打7折'),(17,'新华社北京1月5日电 题：300元增1万名粉丝——起底文娱业数据流量造假产业链','2019-01-02 08:32:30','300元增1万名粉丝 起底文娱业数据流量造假产业链'),(18,'虎扑1月6日讯 今天夏洛特黄蜂队与丹佛掘金队的比赛正在进行中，黄蜂后卫托尼-帕克出场7分钟，已经得到2分2助攻。','2019-01-03 08:36:01','帕克常规赛总助攻超越库西，独享NBA历史第17位'),(19,'【手机中国新闻】在小米之家的众多产品中，小米移动电源可谓是最受宠的产品之一。不过小米移动电源的升级速度并不快，不支持USB-C、PD快充的小米移动电源2系列已经推出多时，亟待更新。1月4日，小米天猫旗舰店发布预告，称小米移动电源3高配版将于1月11日开卖，售价199元。小米移动电源3高配版容量高达20000mAh，采用锂聚合物电芯，支持PD协议的45W充电头可以在4.5小时内把它充满电！小米移动电源3拥有两个USB-A、一个USB-C（Type-C）接口，三个接口均可实现输出，USB-C既能输出也能输入。','2019-01-03 08:36:33','小米移动电源3高配版官宣 2万mAh/45W快充/199元！'),(20,'2018年12月29日16时00分，我国在酒泉卫星发射中心用长征二号丁运载火箭（及远征三号上面级），成功将6颗云海二号卫星和搭载发射的鸿雁星座首颗试验星送入预定轨道。 @我的太空微博 图','2019-01-03 08:36:59','中国发射6颗云海二号卫星 今年航天发射完美收官'),(21,'近年来，电商高速发展，但是也不可避免地带来了诸多问题，虚假宣传、假货泛滥、产品质量、售后服务等问题层出不穷，法律跟不上时代发展的步伐，总有商家在法律灰色地带的危险边缘徘徊。消费者投诉无门的案例数不胜数，最终只能含着泪默默啃下苦果，因此对电商领域立法是大势所趋，人心所向。','2019-01-04 08:44:53','电商法落地细则解读 哪些属于电商法的打击范畴'),(22,'吃脑花补脑？吃鱼眼明目？吃腰花补肾？怀孕吃了兔子娃儿要兔唇？迷药“一闻就倒”“一拍就晕”？对于七大姑八大姨铺天盖地的“祖传秘方”“民间偏方”，你是不是听到脑壳都大了？给妈老汉儿科普“吃肉喝汤哪个更有营养”是不是嘴皮子都说干了？别怕，1月5日，四川大学华西临床医学院/华西医院与新华文轩出版传媒股份有限公司共同打造的《华西医学大系·华西医院辟谣小分队医学科普读本系列》正式首发，看一号难求的专家用正经的“椒盐川普”摆正宗的医学龙门阵。用“椒盐川普”摆医学龙门阵','2019-01-04 08:45:23','吃脑花补脑？ 华西科普读本用\"椒盐川普\"摆正经医学龙门阵'),(23,'1905电影网讯 于2018年春节档上映的电影《红海行动》作为国产军事题材的标杆之作，以黑马之姿在史上最强春节档之中杀出重围，斩获36.5亿票房，不仅逆袭成为春节档票房冠军，更是占据了2018年度最高票房宝座。而林超贤则凭借《红海行动》锁定了2018年度最高票房导演，同时也成为中国电影史上票房成绩TOP2的电影导演。林超贤锁定2018年度最高票房导演  众演员暖心祝贺','2019-01-04 08:46:06','林超贤成年度最高票房导演 热拍新片搭档彭于晏');

/*Table structure for table `my_users` */

DROP TABLE IF EXISTS `my_users`;

CREATE TABLE `my_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdate` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

/*Data for the table `my_users` */

insert  into `my_users`(`id`,`createdate`,`email`,`name`,`password`,`role`) values (16,'2019-01-05 20:04:02','847681488@qq.com','cyw','$2a$10$nfQwoHbiNJmzKm/LcdMUCOp0bkH352S9ZTJJCBSymnswtPVaOzI.y','user'),(18,'2018-12-23 03:41:27','84497@qq.com','onetwo','$2a$10$Ybh32MjPSHwO/X3elGCo6ukprICAChVxUDsTY5q./ExVpIHX9N.Xi','admin'),(19,'2019-01-01 20:04:06','847681488@qq.com','tomax','$2a$10$WSs7Sh0lgXogh/qZWPD8PuusHpqRS2fMU3nZ0U/P5aS27FhEEmaba','admin'),(22,'2019-01-05 12:13:15','350446550@163.com','addoneg','$2a$10$tM.7Z/Fj78g79WebTr8WJ.kyD2vxT0ajE/CwV9Ro1rfDr/GgBjvsK','admin'),(24,'2019-01-06 00:38:52','wengxf@nuaa.edu.cn','wengxiaofan','$2a$10$INBF3XnPoOgq8OL11EajLe01jmbkFyds6rtP6xDbC8SeuUrrzjIlW','user'),(25,'2019-01-06 00:39:26','jinqw@nuaa.edu.cn','jinqiwei','$2a$10$gps760BpsKTnbvEWseEVf.1Y8mqvBSf6pUz1IqSuKk6qW0ix6xdTi','user'),(26,'2019-01-06 00:39:51','chenl@163.com','chenlong','$2a$10$/qnZlZDnazQrU3dz2Uas4ebloqJ7iAKzebKmx2MNSvWncPzD14jVW','user'),(27,'2019-01-06 00:41:21','chenlx@163.com','chenlinxian','$2a$10$pBnuElLe2zC21NX8Z5uq8ervKWbWK3v9sfnxMP/.YVxQav71fI5Da','user'),(28,'2019-01-06 00:42:18','zhangjial@qq.com','zhangjialiang','$2a$10$LRDqPUYm4aSHKroOjZnw9e5Z7SAsoFDhlUGW2fCkwno4ce3FBnUXe','user'),(29,'2019-01-06 00:42:51','linchenx@qq.com','linchenxi','$2a$10$8X3Hl0dzj4gBUGRionH3G.glhhzrc9yYJXhc1me69238ryRYyCNm6','user'),(30,'2019-01-06 00:43:27','caixb@126.com','caixioabin','$2a$10$1Q3cbzvH3tB1VXBaRzBdSOF2viME7YIExNN6E6FIyYQoVnVj0L0Pu','user'),(31,NULL,'847681488@qq.com','tomax','$2a$10$qnIiRnuNRS.sesV.QIujnurZUCUTdVPWugIRQqhHZZgW3flzboyxK','admin'),(32,NULL,'847681488@qq.com','tomax','$2a$10$Lx94Qc9SYsevokDzSzn7JOyYLi/myDHz1ULrKb.vH2DJQ1y3f2gii','admin'),(33,NULL,'847681488@qq.com','tomax','$2a$10$3jwBRBY.C8qL0Yo4yxkoIehCPuMhWZkgpSqLXiik79Zy0KHQwMHV2','admin'),(34,NULL,'847681488@qq.com','tomax','$2a$10$LnlI5Gl2RJFJ90jPp/ij3Oq7rjGVJakcb8uPi6vFd.OZ7twmo2xye','admin'),(35,NULL,'847681488@qq.com','tomax','$2a$10$CJRk/sPBAeCQ.n5HTsjc/.ipspUi29y23pg0dX/47Eu0aRe3fpx.6','admin'),(36,NULL,'847681488@qq.com','tomax','$2a$10$FqHUEW2UkjRvLVyRWfvDlekEhF/oA1UWDMtM8j/nUEkHRrBtr0lya','admin'),(37,NULL,'847681488@qq.com','tomax','$2a$10$AMS0k0txBKiOoppilQDsmeQCk6srf7/twcW4/IOMvSmpJBnM48H0q','admin');

/*Table structure for table `persistent_logins` */

DROP TABLE IF EXISTS `persistent_logins`;

CREATE TABLE `persistent_logins` (
  `series` varchar(64) NOT NULL,
  `last_used` datetime NOT NULL,
  `token` varchar(64) NOT NULL,
  `username` varchar(64) NOT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `persistent_logins` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
