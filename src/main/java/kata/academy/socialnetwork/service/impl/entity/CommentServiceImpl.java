package kata.academy.socialnetwork.service.impl.entity;

import kata.academy.socialnetwork.model.entity.Comment;
import kata.academy.socialnetwork.repository.abst.entity.CommentRepository;
import kata.academy.socialnetwork.service.abst.entity.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends AbstractServiceImpl<Comment, Long> implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        super(commentRepository);
        this.commentRepository = commentRepository;
    }

    @Override
    public Page<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }




}
