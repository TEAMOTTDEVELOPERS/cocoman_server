package orangetaxiteam.cocoman.config;


import orangetaxiteam.cocoman.domain.StarRatingRepository;
import orangetaxiteam.cocoman.domain.StarRating;
import orangetaxiteam.cocoman.domain.User;
import orangetaxiteam.cocoman.domain.UserRepository;
import orangetaxiteam.cocoman.domain.Contents;
import orangetaxiteam.cocoman.domain.ContentsRepository;
import orangetaxiteam.cocoman.domain.Genre;
import orangetaxiteam.cocoman.domain.GenreRepository;
import orangetaxiteam.cocoman.domain.Review;
import orangetaxiteam.cocoman.domain.ReviewRepository;
import orangetaxiteam.cocoman.domain.Ott;
import orangetaxiteam.cocoman.domain.OttRepository;
import orangetaxiteam.cocoman.domain.Gender;
import orangetaxiteam.cocoman.domain.RatePlan;
import orangetaxiteam.cocoman.domain.RatePlanRepository;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class InitDummyData implements CommandLineRunner {
    private UserRepository userRepository;
    private ContentsRepository contentsRepository;
    private StarRatingRepository starRatingRepository;
    private GenreRepository genreRepository;
    private ReviewRepository reviewRepository;
    private OttRepository ottRepository;
    private RatePlanRepository ratePlanRepository;

    public InitDummyData(
            UserRepository userRepository,
            ContentsRepository contentsRepository,
            StarRatingRepository starRatingRepository,
            GenreRepository genreRepository,
            ReviewRepository reviewRepository,
            OttRepository ottRepository,
            RatePlanRepository ratePlanRepository
    ) {
        this.userRepository = userRepository;
        this.contentsRepository = contentsRepository;
        this.starRatingRepository = starRatingRepository;
        this.genreRepository = genreRepository;
        this.reviewRepository = reviewRepository;
        this.ottRepository = ottRepository;
        this.ratePlanRepository = ratePlanRepository;
    }

    @Override
    public void run(String... args) {
        List<User> userList = this.insertDummyUsers();
        List<Genre> genreList = this.insertDummyGenres();
        List<Contents> contentsList = this.insertDummyContents(genreList);
        List<Ott> ottList = this.insertDummyOtts(contentsList);

        this.insertDummyRatePlan(ottList);
        this.insertDummyStarRating(contentsList);
        this.insertDummyReview(contentsList, userList);
    }

    private List<User> insertDummyUsers() {
        List<User> userList = new ArrayList<>();

        if (this.userRepository.findAll().isEmpty()) {
            userList.add(
                    this.userRepository.save(
                            User.of(
                                    "testEmail@email.com",
                                    "Haesung",
                                    "1234",
                                    26,
                                    Gender.MALE,
                                    "010-1234-1234",
                                    "",
                                    ""
                            )
                    )
            );
        }

        return userList;
    }

    private List<Genre> insertDummyGenres() {
        List<Genre> genreList = new ArrayList<>();

        if (this.genreRepository.findAll().isEmpty()) {
            genreList.add(
                    this.genreRepository.save(
                            Genre.of("드라마")
                    )
            );

            genreList.add(
                    this.genreRepository.save(
                            Genre.of("모험")
                    )
            );

            genreList.add(
                    this.genreRepository.save(
                            Genre.of("블록버스터")
                    )
            );
        }

        return genreList;
    }

    private List<Contents> insertDummyContents(List<Genre> genreList) {
        List<Contents> contentsList = new ArrayList<>();

        if (this.contentsRepository.findAll().isEmpty()) {
            contentsList.add(this.contentsRepository.save(
                    Contents.of(
                            "기생충",
                            "2019",
                            "한국",
                            131,
                            "15",
                            null,
                            null,
                            null,
                            "전원 백수로 살 길이 막막하지만 사이는 좋은 기택 가족. 장남 기우에게 명문대생 친구 민혁이 연결해 준 고액 과외는 " +
                                    "모처럼 싹튼 고정 수입의 희망이다. 기우는 온 가족의 기대 속에 박 사장 집으로 향한다.",
                            null,
                            null,
                            null,
                            null,
                            new HashSet<>(List.of(genreList.get(0))),
                            null
                    )
            ));

            contentsList.add(this.contentsRepository.save(
                    Contents.of(
                            "해리포터와 마법사의 돌",
                            "2001",
                            "영국",
                            152,
                            "All",
                            null,
                            null,
                            null,
                            "이모네 식구의 갖은 구박을 받으며 살아가던 고아 소년 해리포터. 큰 기대 없이 맞이한 11번째 생일 날, " +
                                    "해리는 호그와트 마법학교에 입학 초대를 받고 자신의 진정한 정체를 알게 된다.",
                            null,
                            null,
                            null,
                            null,
                            new HashSet<>(List.of(genreList.get(1), genreList.get(2))),
                            null
                    )
            ));
        }

        return contentsList;
    }

    private List<Ott> insertDummyOtts(List<Contents> contentsList){
        List<Ott> ottList = new ArrayList<>();
        Set<Contents> netflixContents = new HashSet<>(contentsList);

        if (this.ottRepository.findAll().isEmpty()) {
            ottList.add(this.ottRepository.save(
                    Ott.of(
                            "Netflix",
                            "",
                            netflixContents
                    )
            ));
        }

        return ottList;
    }

    private void insertDummyRatePlan(List<Ott> ottList){
        if (this.ratePlanRepository.findAll().isEmpty()) {
            this.ratePlanRepository.save(RatePlan.of(
                    "Basic",
                    "9,500￦",
                    "SD(480p)",
                    1,
                    ottList.get(0)
            ));

            this.ratePlanRepository.save(RatePlan.of(
                    "Standard",
                    "13,500￦",
                    "HD(1080p)",
                    2,
                    ottList.get(0)
            ));

            this.ratePlanRepository.save(RatePlan.of(
                    "Premium",
                    "17,000￦",
                    "UHD(2160p)+HDR",
                    4,
                    ottList.get(0)
            ));
        }
    }

    private void insertDummyStarRating(List<Contents> contentsList) {
        if (this.starRatingRepository.findAll().isEmpty()) {
            this.starRatingRepository.save(StarRating.of(
                    5.0,
                    null,
                    contentsList.get(0)
            ));

            this.starRatingRepository.save(StarRating.of(
                    3.0,
                    null,
                    contentsList.get(0)
            ));

            this.starRatingRepository.save(StarRating.of(
                    3.5,
                    null,
                    contentsList.get(1)
            ));
        }
    }

    private void insertDummyReview(List<Contents> contentsList, List<User> userList) {
        if (this.reviewRepository.findAll().isEmpty()) {
            this.reviewRepository.save(Review.of(
                    "재밌어요",
                    userList.get(0),
                    contentsList.get(0)
            ));

            this.reviewRepository.save(Review.of(
                    "지루해요",
                    userList.get(0),
                    contentsList.get(0)
            ));
        }
    }


}
