package orangetaxiteam.cocoman.domain;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@Component
public class DefaultPaginationFactory implements PaginationFactory {
    private final static String X_FORWARDED_HOST = "X-Forwarded-Host";
    private final static String X_FORWARDED_PORT = "X-Forwarded-Port";
    private final static String X_FORWARDED_PROTO = "X-Forwarded-Proto";
    private final HttpServletRequest request;

    public DefaultPaginationFactory(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public <T> Pagination<T> create(Page<T> p) {
        return new Pagination<>(
                this.buildNextUrl(p),
                this.buildPreviousUrl(p),
                p.getTotalElements(),
                p.getContent()
        );
    }

    private <T> String buildPreviousUrl(Page<T> p) {
        if (p.isFirst()) {
            return null;
        }

        return this.buildPageReplacedUrl(
                p.getPageable().getPageNumber() - 1
        );
    }

    private <T> String buildNextUrl(Page<T> p) {
        if (p.isLast()) {
            return null;
        }

        return this.buildPageReplacedUrl(
                p.getPageable().getPageNumber() + 1
        );
    }

    private String buildPageReplacedUrl(int page) {
        String url = this.isForwardedRequest(this.request) ? this.getForwardedURL(this.request) : this.request.getRequestURL().toString();
        return UriComponentsBuilder.fromUriString(url)
                .query(this.request.getQueryString())
                .replaceQueryParam("page", page)
                .build()
                .toUriString();
    }

    private boolean isForwardedRequest(HttpServletRequest request) {
        return request.getHeader(X_FORWARDED_HOST) != null
                && request.getHeader(X_FORWARDED_PORT) != null
                && request.getHeader(X_FORWARDED_PROTO) != null;
    }

    private String getForwardedURL(HttpServletRequest request) {
        String protocol = request.getHeader(X_FORWARDED_PROTO);
        String host = request.getHeader(X_FORWARDED_HOST);
        String port = request.getHeader(X_FORWARDED_PORT);
        if (port.equals("80") || port.equals("443")) {
            return protocol + "://" + host + request.getRequestURI();
        }
        return protocol + "://" + host + ":" + port + request.getRequestURI();
    }
}
