package com.vti.rw41.FinalExam.specification;

import com.vti.rw41.FinalExam.entity.Account;
import com.vti.rw41.FinalExam.form.AccountFilterForm;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AccountSpecification {
    @SuppressWarnings("depreation")
    public static Specification<Account> buildWhere(String search, AccountFilterForm filterForm) {
//        if (StringUtils.isEmpty(search)) {
//            return null;
//        }
//        search = search.trim();
//        CustomSpecification name = new CustomSpecification("user_name", search);
//        return Specification.where(name);
//    }
        Specification<Account> where = null;

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            CustomSpecification name = new CustomSpecification("user_name", search);
            where = Specification.where(name);

        }
        if (filterForm != null && filterForm.getMinId() != null) {
            CustomSpecification minId = new CustomSpecification("minId", filterForm.getMinId());
            if (where == null) {
                where = minId;
            } else {
                where = where.and(minId);
            }
        }
        if (filterForm != null && filterForm.getMaxId() != null) {
            CustomSpecification maxId = new CustomSpecification("maxId", filterForm.getMaxId());
            if (where == null) {
                where = maxId;
            } else {
                where = where.and(maxId);
            }
        }
        return where;
    }

    @SuppressWarnings("serial")
    @RequiredArgsConstructor
    static
    class CustomSpecification implements Specification<Account> {
        @NonNull
        private String field;

        @NonNull
        private Object value;


        @Override
        public Predicate toPredicate(Root<Account> root,
                                     CriteriaQuery<?> query,
                                     CriteriaBuilder criteriaBuilder) {
            if (field.equalsIgnoreCase("user_name")) {
                return criteriaBuilder.like(root.get("user_name"), "%" + value.toString() + "%");
            }
            if (field.equalsIgnoreCase("minId")) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("id"), value.toString());
            }
            if (field.equalsIgnoreCase("maxId")) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("id"), value.toString());
            }
            return null;
        }
    }
}
