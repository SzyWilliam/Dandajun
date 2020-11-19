package fdu.ddj.backend;

import fdu.ddj.backend.domain.Activity;
import fdu.ddj.backend.domain.Article;
import fdu.ddj.backend.domain.Keyword;
import fdu.ddj.backend.repository.ActivityRepository;
import fdu.ddj.backend.repository.ArticleRepository;
import fdu.ddj.backend.repository.KeywordRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.sql.Timestamp;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	 @Bean
    public CommandLineRunner dataLoader(ActivityRepository activityRepository, ArticleRepository articleRepository,
                                        KeywordRepository keywordRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                // Create authorities if not exist.
				if (activityRepository.findByName("test").size() == 0){
                    System.out.println("hello");
                    Activity testActivity = new Activity(
                        "test",
                            "中美贸易战对于我国金融业、高科技行业、制造业的影响",
                            "讲座",
                            "这周结束之前",
                            "11月22日（周五）18:30-20:30",
                            "光华楼东辅楼102袁天凡报告厅",
                            "复旦前程协会",
                            "https://mp.weixin.qq.com/s/IUBOSHLWsEzAIZhBgnFBwA",
                            "中美贸易战对于我国金融业、高科技行业、制造业的有什么影响呢？让小编带大家一起了解" +
                                    "一下。中美贸易战，顾名思义，就是中国和美国之间的贸易战，那么众所周知，中美贸易战" +
                                    "会对我国金融业、高科技行业和制造业都产生巨大的影响，不容忽视。以上就是小编了解到的" +
                                    "所有内容了，觉得有帮助的帮小编点个赞吧",
                             Date.valueOf("2020-09-26"),
                            new Timestamp(System.currentTimeMillis())
                    );
                    activityRepository.save(testActivity);
                }

                // Create an admin if not exists.
                if (articleRepository.findByName("test").size() == 0){
                    Article article = new Article(
                            "test",
                            "选课",
                            "第一模块选课排雷",
                            "资治通鉴好课",
                            "www.test.cn",
                            Date.valueOf("2020-10-05"),
                            "资治通鉴真是好课，大家一定要来选",
                            new Timestamp(System.currentTimeMillis())
                    );
                    articleRepository.save(article);
                }


//                if (keywordRepository.findByKey("复旦").size() == 0) {
//                    Keyword keyword = new Keyword(
//                            "复旦",
//                            0L,
//                            0.8
//                    );
//
//                    keywordRepository.save(keyword);
//                }

            }


        };
    }

}
