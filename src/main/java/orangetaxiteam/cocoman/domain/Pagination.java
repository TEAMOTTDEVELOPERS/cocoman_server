package orangetaxiteam.cocoman.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
public class Pagination<T> {
    private PaginationMeta pagination;
    private List<T> results;

    public Pagination(String nextUrl, String previousUrl, Long totalCount, List<T> results) {
        this.pagination = new PaginationMeta(nextUrl, previousUrl, totalCount);
        this.results = results;
    }

    public static <T> Pagination<T> empty() {
        return new Pagination<>(null, null, 0L, Collections.emptyList());
    }

    @Getter
    @Setter
    @NoArgsConstructor
    private static class PaginationMeta {
        private String nextUrl;
        private String previousUrl;
        private Long totalCount;

        public PaginationMeta(String nextUrl, String previousUrl, Long totalCount) {
            this.nextUrl = nextUrl;
            this.previousUrl = previousUrl;
            this.totalCount = totalCount;
        }
    }
}