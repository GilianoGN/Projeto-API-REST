package dio.Projeto_API_REST.service.impl;

import dio.Projeto_API_REST.model.News;
import dio.Projeto_API_REST.repository.NewsRepository;
import dio.Projeto_API_REST.service.NewsService;
import dio.Projeto_API_REST.service.exception.BusinessException;
import dio.Projeto_API_REST.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class NewsServiceImpl implements NewsService {

    private static final Long UNCHANGEABLE_NEWS_ID = 1L;

    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Transactional(readOnly = true)
    public List<News> findAll() {
        return this.newsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public News findById(Long id) {
        return this.newsRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public News create(News NewsToCreate) {
        ofNullable(NewsToCreate.getIcon()).orElseThrow(() -> new BusinessException("News icon must not be null."));
        ofNullable(NewsToCreate.getDescription()).orElseThrow(() -> new BusinessException("News description must not be null."));

        this.validateChangeableId(NewsToCreate.getNews_Id(), "created");

        return this.newsRepository.save(NewsToCreate);
    }

    @Transactional
    public News update(Long id, News NewsToUpdate) {
        this.validateChangeableId(id, "updated");
        News dbNews = this.findById(id);
        if (!dbNews.getNews_Id().equals(NewsToUpdate.getNews_Id())) {
            throw new BusinessException("Update IDs must be the same.");
        }
        dbNews.setIcon(NewsToUpdate.getIcon());
        dbNews.setDescription(NewsToUpdate.getDescription());
        return this.newsRepository.save(dbNews);
    }

    @Transactional
    public void delete(Long id) {
        this.validateChangeableId(id, "deleted");
        News dbNews = this.findById(id);
        this.newsRepository.delete(dbNews);
    }

    private void validateChangeableId(Long id, String operation) {
        if (UNCHANGEABLE_NEWS_ID.equals(id)) {
            throw new BusinessException("News with ID %d can not be %s.".formatted(UNCHANGEABLE_NEWS_ID, operation));
        }
    }
}