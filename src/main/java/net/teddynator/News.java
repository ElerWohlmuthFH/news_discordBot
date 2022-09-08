package net.teddynator;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.*;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class News extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
//        User user = event.getAuthor(); //get message Author

//        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File("config.yml"));

//        String prefix = config.getString("prefix");
        String prefix = "/";

        if (args[0].equalsIgnoreCase(prefix + "news")) {

            event.getChannel().sendMessage("news :)").queue();
        }
    }


    public static void helloWorld() {

        String key = "812717347b1b42eb91d185b0f0f9285c";
        NewsApiClient newsApiClient = new NewsApiClient(key);

        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .q("bitcoin")
                        .language("en")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        System.out.println(response.getArticles().get(0).getTitle());
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );
    }
}
