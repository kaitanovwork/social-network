package kata.academy.socialnetwork.web.init;

import kata.academy.socialnetwork.model.entity.Comment;
import kata.academy.socialnetwork.model.entity.CommentLike;
import kata.academy.socialnetwork.model.entity.Post;
import kata.academy.socialnetwork.model.entity.PostLike;
import kata.academy.socialnetwork.model.entity.Role;
import kata.academy.socialnetwork.model.entity.User;
import kata.academy.socialnetwork.model.entity.UserInfo;
import kata.academy.socialnetwork.model.enums.Gender;
import kata.academy.socialnetwork.model.enums.RoleName;
import kata.academy.socialnetwork.service.abst.entity.CommentLikeService;
import kata.academy.socialnetwork.service.abst.entity.CommentService;
import kata.academy.socialnetwork.service.abst.entity.PostLikeService;
import kata.academy.socialnetwork.service.abst.entity.PostService;
import kata.academy.socialnetwork.service.abst.entity.RoleService;
import kata.academy.socialnetwork.service.abst.entity.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




@Profile("dev")
@RequiredArgsConstructor
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final PostService postService;
    private final CommentService commentService;
    private final PostLikeService postLikeService;
    private final CommentLikeService commentLikeService;

    @Override
    public void run(ApplicationArguments args) {
        Role userRole = new Role();
        userRole.setName(RoleName.USER);
        roleService.save(userRole);

        User user = new User();
        user.setEmail("user");
        user.setPassword("password");
        user.setUserInfo(new UserInfo(
                "David",
                "Beckham",
                "LA",
                25,
                Gender.MALE
        ));
        userService.save(user);

        User elon = new User();
        elon.setEmail("testone");
        elon.setPassword("passone");
        elon.setUserInfo(new UserInfo("Elon", "Musk", "Stanford", 51, Gender.MALE));
        elon.setRole(userRole);
        userService.save(elon);

        User amber = new User();
        amber.setEmail("testtwo");
        amber.setPassword("passtwo");
        amber.setUserInfo(new UserInfo("Amber ", "Heard", "Austin", 36, Gender.FEMALE));
        amber.setRole(userRole);
        userService.save(amber);

        User miron = new User();
        miron.setEmail("testthree");
        miron.setPassword("passthree");
        miron.setUserInfo(new UserInfo("Miron", "Fyodorov", "London", 37, Gender.MALE));
        miron.setRole(userRole);
        userService.save(miron);

        User vyacheslav  = new User();
        vyacheslav .setEmail("testfour");
        vyacheslav .setPassword("passfour");
        vyacheslav .setUserInfo(new UserInfo("Vyacheslav ", "KPSS", "Khabarovsk", 32, Gender.MALE));
        vyacheslav .setRole(userRole);
        userService.save(vyacheslav);


        Post postone = new Post();
        postone.setTitle("Do you like twitter?");
        postone.setText("Do you like twitter? My parents give me some pocket money and i think about this small deal of purchasing that.");
        List<String> tagsone = Arrays.asList("Twi", "Editbutton", "bitcoinstopfall");
        postone.setTags(tagsone);
        postone.setUser(elon);
        postService.save(postone);

        Post posttwo = new Post();
        posttwo.setTitle("Wierd  names");
        posttwo.setText("Recently i've heard smtg like \"witch and slave\". Seems kinda funny isn't it ?");
        List<String> tagstwo = Arrays.asList("russian", "funny", "names");
        posttwo.setTags(tagstwo);
        posttwo.setUser(elon);
        postService.save(posttwo);

        Post postthree = new Post();
        postthree.setTitle("Looking for engineers");
        postthree.setText("We have some vacancies open for new employees, click the link below and start you own trip in musk team");
        List<String> tagthree = Arrays.asList("job", "vacancies", "muskteam");
        postthree.setTags(tagthree);
        postthree.setUser(elon);
        postService.save(postthree);

        Post postfour = new Post();
        postfour.setTitle("Почему?");
        postfour.setText("Почему нет игры где ты типо грибок? И ты прыгаешь на супермарио!");
        List<String> tagfour = Arrays.asList("mario", "game");
        postfour.setTags(tagfour);
        postfour.setUser(miron);
        postService.save(postfour);

        Post postfive = new Post();
        postfive.setTitle("Яхонтовые вы мои");
        postfive.setText("Хочу вас от всего сердца поблагодорить вас за поддержку моего последнего альбома. Хочу сказать спасибо за терпение в течении многих лет.");
        List<String> tagfive = Arrays.asList("КрасотаУродство", "Альбом");
        postfive.setTags(tagfive);
        postfive.setUser(miron);
        postService.save(postfive);


        Post postsix = new Post();
        postsix.setTitle("Шалом");
        postsix.setText("Ребята, я знаю дена бро, я знаю ганса, знаю нолимита, знаю всех кто в Англии... Объясните оп хорошему, кто такой MidWay? ");
        List<String> tagsix = Arrays.asList("greenpark", "gang");
        postsix.setTags(tagsix);
        postsix.setUser(miron);
        postService.save(postsix);

        Post postseven = new Post();
        postseven.setTitle("Стрим сегодня");
        postseven.setText("Сегодня разбор трека \" кто убил марка \". Легендарное возвращение легенды, ссылка на донат будет в описании под стримом");
        List<String> tagseven = Arrays.asList("трек", "стрим", "КтоУбилМарка");
        postseven.setTags(tagseven);
        postseven.setUser(vyacheslav);
        postService.save(postseven);

        Post posteight = new Post();
        posteight.setTitle("Вы только послушайте эти панчи у Мирона");
        posteight.setText("\" МОЙ ФЛОУ АТАКА ДДОС У НАС УДАЛЕННЫЙ ДО-О-О-О-ОСТУП\" - и вот это у вас вызывает восторг?");
        List<String> tageight = Arrays.asList("oxana", "battle", "clown");
        posteight.setTags(tageight);
        posteight.setUser(vyacheslav);
        postService.save(postfour);

        Post postnine = new Post();
        postnine.setTitle("Why he is not looking at me ");
        postnine.setText("He doesn't look at me once entire trial... I dont understand why... ");
        List<String> tagnine = Arrays.asList("trial", "depp");
        postnine.setTags(tagnine);
        postnine.setUser(amber);
        postService.save(postnine);

        Post postten = new Post();
        postten.setTitle("seven millions $");
        postten.setText("Any way I guess that I won this trial at all. Not a trial, it's better to say a war with him and government");
        List<String> tagsten = Arrays.asList("money", "trial");
        postten.setTags(tagsten);
        postten.setUser(amber);
        postService.save(postfour);

        Comment firstCommentToPostOne = new Comment();
        firstCommentToPostOne.setPost(postone);
        firstCommentToPostOne.setText("Sure, u need to buy it. (Hello my gang<3)");
        firstCommentToPostOne.setUser(miron);
        commentService.save(firstCommentToPostOne);

        Comment secondCommentToPostOne = new Comment();
        secondCommentToPostOne.setPost(postone);
        secondCommentToPostOne.setText("People in twitter are very rude! Buy and that delete this");
        secondCommentToPostOne.setUser(amber);
        commentService.save(secondCommentToPostOne);

        Comment firstCommentToPostTwo = new Comment();
        firstCommentToPostTwo.setPost(posttwo);
        firstCommentToPostTwo.setText("So u think that u child's name isn't funny right?");
        firstCommentToPostTwo.setUser(vyacheslav);
        commentService.save(firstCommentToPostTwo);

        Comment secondCommentToPostTwo = new Comment();
        secondCommentToPostTwo.setPost(posttwo);
        secondCommentToPostTwo.setText("And that person sad that? LOL");
        secondCommentToPostTwo.setUser(amber);
        commentService.save(secondCommentToPostTwo);

        Comment thirdCommentToPostTwo = new Comment();
        thirdCommentToPostTwo.setPost(posttwo);
        thirdCommentToPostTwo.setText("Sorry, I am not think about that enough");
        thirdCommentToPostTwo.setUser(elon);
        commentService.save(thirdCommentToPostTwo);

        Comment fourCommentToPostTwo = new Comment();
        fourCommentToPostTwo.setPost(posttwo);
        fourCommentToPostTwo.setText("Lol, u right man, directly in the middle");
        fourCommentToPostTwo.setUser(miron);
        commentService.save(fourCommentToPostTwo);

        Comment firstCommentToPostThree = new Comment();
        firstCommentToPostThree.setPost(postthree);
        firstCommentToPostThree.setText("Есть у меня один кандидат, тот еще технарь");
        firstCommentToPostThree.setUser(vyacheslav);
        commentService.save(firstCommentToPostThree);

        Comment secondCommentToPostThree = new Comment();
        secondCommentToPostThree.setPost(postthree);
        secondCommentToPostThree.setText("I can act like engineer");
        secondCommentToPostThree.setUser(amber);
        commentService.save(secondCommentToPostThree);

        Comment firstCommentToPostFour = new Comment();
        firstCommentToPostFour.setPost(postfour);
        firstCommentToPostFour.setText("А если б такая игра и был, то миллионы в игру не играли бы");
        firstCommentToPostFour.setUser(miron);
        commentService.save(firstCommentToPostFour);

        Comment secondCommentToPostFour = new Comment();
        secondCommentToPostFour.setPost(postfour);
        secondCommentToPostFour.setText("Вот это мы называем творчетсво");
        secondCommentToPostFour.setUser(vyacheslav);
        commentService.save(secondCommentToPostFour);

        Comment firstCommentToPostFive = new Comment();
        firstCommentToPostFive.setPost(postfour);
        firstCommentToPostFive.setText("Отдельно хочу передать привет моему твиттергенг. Я знаю вы ждете моего сообщения, пишете очень много сообщений, обязательно выйду на связь скоро");
        firstCommentToPostFive.setUser(miron);
        commentService.save(firstCommentToPostFive);

        Comment secondCommentToPostFive = new Comment();
        secondCommentToPostFive.setPost(postfive);
        secondCommentToPostFive.setText("Он сказал яхонтовые)...");
        secondCommentToPostFive.setUser(vyacheslav);
        commentService.save(secondCommentToPostFive);

        Comment thirdCommentToPostFive = new Comment();
        thirdCommentToPostFive.setPost(postfive);
        thirdCommentToPostFive.setText("Ой как удалить можно коммент");
        thirdCommentToPostFive.setUser(vyacheslav);
        commentService.save(thirdCommentToPostFive);


        Comment firstCommentToPostSix = new Comment();
        firstCommentToPostSix.setPost(postsix);
        firstCommentToPostSix.setText("Никто и этих-то твоих воображаемых друзей не знает, успокойся");
        firstCommentToPostSix.setUser(vyacheslav);
        commentService.save(firstCommentToPostSix);

        Comment secondCommentToPostSix = new Comment();
        secondCommentToPostSix.setPost(postsix);
        secondCommentToPostSix.setText("Somebody tells my, that u have some mental diseases... I like psycho-unstable men, so text me in dm ");
        secondCommentToPostSix.setUser(amber);
        commentService.save(secondCommentToPostSix);

        Comment firstCommentToPostSeven = new Comment();
        firstCommentToPostSeven.setPost(postseven);
        firstCommentToPostSeven.setText("I've listen whole track with subtitles and still don't know who kill him");
        firstCommentToPostSeven.setUser(elon);
        commentService.save(firstCommentToPostSeven);

        Comment firstCommentToPostEight = new Comment();
        firstCommentToPostEight.setPost(posteight);
        firstCommentToPostEight.setText("I think that u could be a perfect systems admin. What u think about make some career it there?");
        firstCommentToPostEight.setUser(elon);
        commentService.save(firstCommentToPostEight);

        Comment secondCommentToPostEight = new Comment();
        secondCommentToPostEight.setPost(posteight);
        secondCommentToPostEight.setText("AntiHype fan, Elon?");
        secondCommentToPostEight.setUser(miron);
        commentService.save(secondCommentToPostEight);

        Comment firstCommentToPostNine = new Comment();
        firstCommentToPostNine.setPost(postnine);
        firstCommentToPostNine.setText("Don't pay so much attention in that, it's all lost in th past");
        firstCommentToPostNine.setUser(elon);
        commentService.save(firstCommentToPostNine);

        Comment secondCommentToPostNine = new Comment();
        secondCommentToPostNine.setPost(postnine);
        secondCommentToPostNine.setText("Я думаю мистер Депп устал от такого в отношениях");
        secondCommentToPostNine.setUser(vyacheslav);
        commentService.save(secondCommentToPostNine);

        Comment firstCommentToPostTen = new Comment();
        firstCommentToPostTen.setPost(postten);
        firstCommentToPostTen.setText("Well done!");
        firstCommentToPostTen.setUser(elon);
        commentService.save(firstCommentToPostTen);

        Comment secondCommentToPostTen = new Comment();
        secondCommentToPostTen.setPost(postten);
        secondCommentToPostTen.setText("Надо поделиться. Нам авантюристам надо продолжать похожденья");
        secondCommentToPostTen.setUser(miron);
        commentService.save(secondCommentToPostTen);

        postLikeService.save(new PostLike(null, postone, miron, true));
        postLikeService.save(new PostLike(null, postone, vyacheslav, true));
        postLikeService.save(new PostLike(null, postone, amber, true));
        postLikeService.save(new PostLike(null, posttwo, vyacheslav, false));
        postLikeService.save(new PostLike(null, posttwo, amber, false));
        postLikeService.save(new PostLike(null, posttwo, miron, true));
        postLikeService.save(new PostLike(null, postthree, vyacheslav, true));
        postLikeService.save(new PostLike(null, postthree, miron, true));
        postLikeService.save(new PostLike(null, postthree, amber, true));
        postLikeService.save(new PostLike(null, postfour, vyacheslav, false));
        postLikeService.save(new PostLike(null, postfive, vyacheslav, false));
        postLikeService.save(new PostLike(null, postfive, amber, true));
        postLikeService.save(new PostLike(null, postfive, elon, true));
        postLikeService.save(new PostLike(null, postsix, amber, true));
        postLikeService.save(new PostLike(null, postsix, vyacheslav, false));
        postLikeService.save(new PostLike(null, postseven, elon, true));
        postLikeService.save(new PostLike(null, postseven, amber, true));
        postLikeService.save(new PostLike(null, posteight, elon, true));
        postLikeService.save(new PostLike(null, posteight, amber, false));
        postLikeService.save(new PostLike(null, postnine, elon, true));
        postLikeService.save(new PostLike(null, postnine, miron, false));
        postLikeService.save(new PostLike(null, postnine, vyacheslav, false));
        postLikeService.save(new PostLike(null, postten, miron, false));
        postLikeService.save(new PostLike(null, postten, vyacheslav, true));
        postLikeService.save(new PostLike(null, postten, elon, true));


        commentLikeService.save(new CommentLike(null, false, firstCommentToPostOne, vyacheslav));
        commentLikeService.save(new CommentLike(null, true, firstCommentToPostOne, amber));
        commentLikeService.save(new CommentLike(null, false, secondCommentToPostOne, miron));
        commentLikeService.save(new CommentLike(null, false, secondCommentToPostOne, vyacheslav));
        commentLikeService.save(new CommentLike(null, true, firstCommentToPostTwo, miron));
        commentLikeService.save(new CommentLike(null, true, firstCommentToPostTwo, amber));
        commentLikeService.save(new CommentLike(null, true, firstCommentToPostTwo, elon));
        commentLikeService.save(new CommentLike(null, true, secondCommentToPostTwo, vyacheslav));
        commentLikeService.save(new CommentLike(null, true, thirdCommentToPostTwo, miron));
        commentLikeService.save(new CommentLike(null, true, fourCommentToPostTwo, amber));
        commentLikeService.save(new CommentLike(null, true, firstCommentToPostThree, elon));
        commentLikeService.save(new CommentLike(null, true, secondCommentToPostThree, elon));
        commentLikeService.save(new CommentLike(null, true, firstCommentToPostFour, elon));
        commentLikeService.save(new CommentLike(null, true, firstCommentToPostFour, amber));
        commentLikeService.save(new CommentLike(null, false, firstCommentToPostFour, vyacheslav));
        commentLikeService.save(new CommentLike(null, true, secondCommentToPostFour, elon));
        commentLikeService.save(new CommentLike(null, false, secondCommentToPostFour, amber));
        commentLikeService.save(new CommentLike(null, true, firstCommentToPostFive, elon));
        commentLikeService.save(new CommentLike(null, true, firstCommentToPostFive, amber));
        commentLikeService.save(new CommentLike(null, true, secondCommentToPostFive, elon));
        commentLikeService.save(new CommentLike(null, true, secondCommentToPostFive, amber));
        commentLikeService.save(new CommentLike(null, true, thirdCommentToPostFive, elon));
        commentLikeService.save(new CommentLike(null, true, thirdCommentToPostFive, amber));
        commentLikeService.save(new CommentLike(null, true, thirdCommentToPostFive, miron));
        commentLikeService.save(new CommentLike(null, false, firstCommentToPostSix, amber));
        commentLikeService.save(new CommentLike(null, false, secondCommentToPostSix, elon));
        commentLikeService.save(new CommentLike(null, true, firstCommentToPostSeven, vyacheslav));
        commentLikeService.save(new CommentLike(null, false, firstCommentToPostSeven, amber));
        commentLikeService.save(new CommentLike(null, true, firstCommentToPostEight, miron));
        commentLikeService.save(new CommentLike(null, true, firstCommentToPostEight, amber));
        commentLikeService.save(new CommentLike(null, true, secondCommentToPostEight, amber));
        commentLikeService.save(new CommentLike(null, false, firstCommentToPostNine, miron));
        commentLikeService.save(new CommentLike(null, true, firstCommentToPostTen, vyacheslav));
        commentLikeService.save(new CommentLike(null, true, firstCommentToPostTen, amber));
        commentLikeService.save(new CommentLike(null, true, secondCommentToPostTen, amber));

    }
}
