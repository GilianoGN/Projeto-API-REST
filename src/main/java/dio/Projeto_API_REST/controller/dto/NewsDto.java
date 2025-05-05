package dio.Projeto_API_REST.controller.dto;

import dio.Projeto_API_REST.model.News;

public record NewsDto(Long news_id, String icon, String description) {

    public NewsDto(News model) {
        this(model.getNews_Id(), model.getIcon(), model.getDescription());
    }
    
    public News toModel() {
        News model = new News();
        model.setNews_Id(this.news_id);
        model.setIcon(this.icon);
        model.setDescription(this.description);
        return model;
    }
}