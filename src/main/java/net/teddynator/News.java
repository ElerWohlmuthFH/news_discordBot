package net.teddynator;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.request.SourcesRequest;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.kwabenaberko.newsapilib.models.response.SourcesResponse;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.lang.reflect.Array;
import java.util.Arrays;

public class News extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        String prefix = "/";

        if (args[0].equalsIgnoreCase(prefix + "news")) {

            //generates search term
            String searchTerm = "";
            for (int i = 0; i < args.length - 1; i++) {
                searchTerm = searchTerm + " " + args[i];
            }

            String language;
            String[] languages = {"de", "en"};


            if (Arrays.asList(languages).contains(args[args.length - 2]))
//                    || !isNumeric(args[args.length - 1]) && args[args.length - 1].length() == 2)
            {
                language = args[args.length - 2];
            } else if (Arrays.asList(languages).contains(args[args.length - 1])) {
                language = args[args.length - 1];

            } else {
                language = "en";
            }
            //checks if last arg is numeric
            if (isNumeric(args[args.length - 1])) {
                int results = Integer.parseInt(args[args.length - 1]);
                news(event.getChannel().asTextChannel(), searchTerm, language, results);
            } else if (args[args.length - 1] != null || !isNumeric(args[args.length - 1])) {
                news(event.getChannel().asTextChannel(), searchTerm, language, 5); //default result count
            }
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    public void news(TextChannel channel, String keyword, String language, int results) {

        String key = "812717347b1b42eb91d185b0f0f9285c";
        NewsApiClient newsApiClient = new NewsApiClient(key);

        newsApiClient.getEverything(

                new EverythingRequest.Builder()
                        .q(keyword)
                        .language(language)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
//                        System.out.println(response.getArticles().get(0).getTitle());

                        try {
                            for (int i = 0; i < results; i++) {
                                String news = response.getArticles().get(i).getUrl();
                                channel.sendMessage(news).queue();
                            }
                        } catch (IndexOutOfBoundsException e) {
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());

                    }
                }
        );


    }


}
