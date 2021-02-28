package orangetaxiteam.cocoman.domain;

import org.springframework.data.domain.Page;

public abstract class PaginationFinder {
    private final PaginationFactory paginationFactory;

    public PaginationFinder(PaginationFactory paginationFactory) {
        this.paginationFactory = paginationFactory;
    }

    protected <T> Pagination<T> findAll(PageFinder<T> pageFinder) {
        Page<T> p = pageFinder.find();
        return this.paginationFactory.create(p);
    }

    protected interface PageFinder<T> {
        Page<T> find();
    }
}
