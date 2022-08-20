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
            CustomSpecification name = new CustomSpecification("username", search);
            CustomSpecification first_name = new CustomSpecification("firstname", search);
            CustomSpecification last_name = new CustomSpecification("lastname", search);

            where = Specification.where(name).or(first_name).or(last_name);

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
        if(filterForm != null && filterForm.getRole() != null) {
            CustomSpecification role = new CustomSpecification("role" , filterForm.getRole());
            if(where == null) {
                where = role;
            }else  {
                where = where.and(role);
            }
        }
        if(filterForm != null && filterForm.getDepartmentName() != null) {
            CustomSpecification departmentName = new CustomSpecification("departmentName", filterForm.getDepartmentName());
            if(where == null) {
                where = departmentName;
            }else  {
                where = where.and(departmentName);
            }
        }
        return where;
    }

    @SuppressWarnings("serial")
    @RequiredArgsConstructor
    static class CustomSpecification implements Specification<Account> {
        @NonNull
        private String field;

        @NonNull
        private Object value;


        @Override
        public Predicate toPredicate(Root<Account> root,
                                     CriteriaQuery<?> query,
                                     CriteriaBuilder criteriaBuilder) {
            if (field.equalsIgnoreCase("username")) {
                return criteriaBuilder.like(root.get("username"), "%" + value.toString() + "%");
            }
            if (field.equalsIgnoreCase("firstname")) {
                return criteriaBuilder.like(root.get("firstname"), "%" + value.toString() + "%");
            }
            if (field.equalsIgnoreCase("lastname")) {
                return criteriaBuilder.like(root.get("lastname"), "%" + value.toString() + "%");
            }
            if (field.equalsIgnoreCase("minId")) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("id"), value.toString());
            }
            if (field.equalsIgnoreCase("maxId")) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("id"), value.toString());
            }
            if (field.equalsIgnoreCase("role")) {
                return criteriaBuilder.equal(root.get("role"), value);
            }
            if (field.equalsIgnoreCase("departmentName")) {
                return criteriaBuilder.equal(root.get("department").get("name"), value.toString());
            }

            return null;
        }
    }
}
