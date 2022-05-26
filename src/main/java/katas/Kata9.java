package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();
        //ImmutableList.of(ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl"));
        return movieLists.stream()
                .flatMap(m-> m.getVideos().stream()
                        .map(video-> ImmutableMap.of("id",video.getId(),"title", video.getTitle(), "time", video.getInterestingMoments().stream().map(middle -> middle.getTime()), "boxart",video.getBoxarts()
                                .stream().reduce((a,b) -> a.getWidth() * a.getHeight() < b.getWidth() * b.getHeight() ? a:b).map(p-> ImmutableMap.of("boxart",p.getUrl()))))).collect(Collectors.toList());
    }
}
