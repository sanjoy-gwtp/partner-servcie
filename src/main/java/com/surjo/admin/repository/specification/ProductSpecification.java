package com.surjo.admin.repository.specification;


import com.surjo.admin.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * Created by sanjoy on 7/17/18.
 */
public class ProductSpecification {

    public static Specification<ProductEntity> searchProductByName(String name) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            Predicate namePredicate = cb.like(cb.lower(root.get("name")), "%"+name+"%");
            Predicate shortDetailsPredicate = cb.like(cb.lower(root.get("shortDetails")),"%"+name+"%");
            Predicate descriptionPredicate = cb.like(cb.lower(root.get("description")), "%"+name+"%");
            if(name != null) {
                predicate = cb.or(namePredicate,shortDetailsPredicate,descriptionPredicate);
            }
            return predicate;
        };
    }
}
