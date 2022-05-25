package katas;

import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.Movie;
import util.DataUtil;

import java.util.List;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {
    public static String execute() {
        List<Movie> movies = DataUtil.getMovies();

        return movies.stream().flatMap(b -> b.getBoxarts().stream()).reduce((x,y) -> x.getWidth() * x.getHeight() > y.getWidth() * y.getHeight() ? x:y).map(BoxArt::getUrl).toString();
    }
}
