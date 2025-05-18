package gr.dimitriosdrakopoulos.projects.auto_track_pro.core.specifications;

import org.springframework.data.jpa.domain.Specification;

import gr.dimitriosdrakopoulos.projects.auto_track_pro.core.enums.RoleType;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.Admin;
import gr.dimitriosdrakopoulos.projects.auto_track_pro.model.User;
import jakarta.persistence.criteria.Join;

public class AdminSpecification {

    private AdminSpecification() {

    }
    
    public static Specification<Admin> adminUsernameIs(String username) {
        return ((root, query, criteriaBuilder) -> {
            if (username ==null || username.isBlank()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }

            Join<Admin, User> user = root.join("user");
            return criteriaBuilder.equal(user.get("username"), username);
        });
    }

    public static Specification<Admin> adminUserEmailIs(String email) {
        return ((root, query, criteriaBuilder) -> {
            if (email ==null || email.isBlank()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }

            Join<Admin, User> user = root.join("user");
            return criteriaBuilder.equal(user.get("email"), email);
        });
    }

    public static Specification<Admin> adminUserIsActive(Boolean isActive) {
        return ((root, query, criteriaBuilder) -> {
            if (isActive ==null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }

            Join<Admin, User> user = root.join("user");
            return criteriaBuilder.equal(user.get("isActive"), isActive);
        });
    }

    public static Specification<Admin> adminUserRole(RoleType role) {
        return ((root, query, criteriaBuilder) -> {
            if (role ==null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }

            Join<Admin, User> user = root.join("user");
            return criteriaBuilder.equal(user.get("role"), role);
        });
    }

    public static Specification<Admin> adminStringFieldLike(String field, String value) {
        return ((root, query, criteriaBuilder) -> {
            if (value == null || value.trim().isEmpty()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            return criteriaBuilder.like(criteriaBuilder.upper(root.get(field)), "%" + value.toUpperCase() + "%");   // case-insensitive search
        });
    }
}
