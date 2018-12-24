package com.interview.practice.jee.atm.service.provider;

import com.interview.practice.jee.atm.model.card.CardDao;
import com.interview.practice.jee.atm.model.user.UserDao;
import com.interview.practice.jee.atm.service.AccountOperationsService;
import com.interview.practice.jee.atm.service.CardService;
import com.interview.practice.jee.atm.service.Service;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactoryImpl implements ServiceFactory {
    private static final ServiceFactoryImpl INSTANCE = new ServiceFactoryImpl();
    private static final Map<Class<? extends Service>, Service> SERVICE_MAP = new HashMap<>();

    static {
        CardDao cardDao = new CardDao();
        UserDao userDao = new UserDao();
        SERVICE_MAP.put(CardService.class, new CardService(cardDao, userDao));
        SERVICE_MAP.put(AccountOperationsService.class, new AccountOperationsService());
    }

    private ServiceFactoryImpl(){}

    public static ServiceFactoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Service> T get(Class<T> serviceClass) {
        return (T) SERVICE_MAP.get(serviceClass);
    }
}
