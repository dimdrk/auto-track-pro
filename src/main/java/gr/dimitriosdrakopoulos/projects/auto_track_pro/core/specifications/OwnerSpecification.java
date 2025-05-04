package gr.dimitriosdrakopoulos.projects.auto_track_pro.core.specifications;

import org.springframework.data.jpa.domain.Specification;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.Owner;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.User;
import jakarta.persistence.criteria.Join;

public class OwnerSpecification {

    private OwnerSpecification() {

    }

    public static Specification<Owner> ownerUsernameIs(String username) {
        return ((root, query, criteriaBuilder) -> {
            if (username == null || username.isBlank()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            Join<Owner, User> user = root.join("user");
            return criteriaBuilder.equal(user.get("username"), username);
        });
    }

    public static Specification<Owner> ownerUserEmailIs(String email) {
        return ((root, query, criteriaBuilder) -> {
            if (email ==null || email.isBlank()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }

            Join<Owner, User> user = root.join("user");
            return criteriaBuilder.equal(user.get("email"), email);
        });
    }

    public static Specification<Owner> ownerUserIsActive(Boolean isActive) {
        return ((root, query, criteriaBuilder) -> {
            if (isActive ==null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }

            Join<Owner, User> user = root.join("user");
            return criteriaBuilder.equal(user.get("isActive"), isActive);
        });
    }

    public static Specification<Owner> ownerStringFieldLike(String field, String value) {
        return ((root, query, criteriaBuilder) -> {
            if (value == null || value.trim().isEmpty()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            return criteriaBuilder.like(criteriaBuilder.upper(root.get(field)), "%" + value.toUpperCase() + "%");
        });
    }
    
}
