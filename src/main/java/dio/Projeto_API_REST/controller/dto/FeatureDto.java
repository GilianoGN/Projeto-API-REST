package dio.Projeto_API_REST.controller.dto;

import dio.Projeto_API_REST.model.Feature;

public record FeatureDto(Long feature_id, String icon, String description) {

    public FeatureDto(Feature model) {
        this(model.getFeature_Id(), model.getIcon(), model.getDescription());
    }
    
    public Feature toModel() {
        Feature model = new Feature();
        model.setFeature_Id(this.feature_id);
        model.setIcon(this.icon);
        model.setDescription(this.description);
        return model;
    }
}
