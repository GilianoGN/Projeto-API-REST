package dio.Projeto_API_REST.service.impl;

import dio.Projeto_API_REST.model.Feature;
import dio.Projeto_API_REST.repository.FeatureRepository;
import dio.Projeto_API_REST.service.FeatureService;
import dio.Projeto_API_REST.service.exception.BusinessException;
import dio.Projeto_API_REST.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class FeatureServiceImpl implements FeatureService {

    private static final Long UNCHANGEABLE_FEATURE_ID = 1L;

    private final FeatureRepository featureRepository;

    public FeatureServiceImpl(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    @Transactional(readOnly = true)
    public List<Feature> findAll() {
        return this.featureRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Feature findById(Long id) {
        return this.featureRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Feature create(Feature FeatureToCreate) {
        ofNullable(FeatureToCreate.getIcon()).orElseThrow(() -> new BusinessException("Feature icon must not be null."));
        ofNullable(FeatureToCreate.getDescription()).orElseThrow(() -> new BusinessException("Feature description must not be null."));

        this.validateChangeableId(FeatureToCreate.getFeature_Id(), "created");

        return this.featureRepository.save(FeatureToCreate);
    }

    @Transactional
    public Feature update(Long id, Feature FeatureToUpdate) {
        this.validateChangeableId(id, "updated");
        Feature dbFeature = this.findById(id);
        if (!dbFeature.getFeature_Id().equals(FeatureToUpdate.getFeature_Id())) {
            throw new BusinessException("Update IDs must be the same.");
        }
        dbFeature.setIcon(FeatureToUpdate.getIcon());
        dbFeature.setDescription(FeatureToUpdate.getDescription());
        return this.featureRepository.save(dbFeature);
    }

    @Transactional
    public void delete(Long id) {
        this.validateChangeableId(id, "deleted");
        Feature dbNews = this.findById(id);
        this.featureRepository.delete(dbNews);
    }

    private void validateChangeableId(Long id, String operation) {
        if (UNCHANGEABLE_FEATURE_ID.equals(id)) {
            throw new BusinessException("Feature with ID %d can not be %s.".formatted(UNCHANGEABLE_FEATURE_ID, operation));
        }
    }
}