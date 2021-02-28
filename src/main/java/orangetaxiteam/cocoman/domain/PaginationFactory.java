package orangetaxiteam.cocoman.domain;

import org.springframework.data.domain.Page;

public interface PaginationFactory {
    <T> Pagination<T> create(Page<T> p);
}
