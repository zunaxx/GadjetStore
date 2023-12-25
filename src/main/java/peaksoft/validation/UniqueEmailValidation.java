package peaksoft.validation;

import peaksoft.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class UniqueEmailValidation implements ConstraintValidator<UniqueEmail,String> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (email == null){
            return true;
        }
        List<User> existingEmails= entityManager.createQuery("select u from User u where u.email=: email", User.class)
                .setParameter("email",email).getResultList();
        return existingEmails.isEmpty();
    }
}
