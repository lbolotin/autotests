//package Lesson6.entities;
//
//import Lesson6.entities.User;
//import org.apache.thrift.TException;
//import ru.alfabank.tests.core.helpers.PropertyLoader;
//import ru.alfabank.thrift.accounts.AccountsTapiService;
//import ru.alfabank.thrift.accounts.OperationType;
//import ru.alfabank.thrift.auth.entity.UserData;
//import ru.alfabank.thrift.entities.Account;
//import ru.alfabank.thrift.entities.Customer;
//import Lesson6.utils.ThriftClients;
//
//public class CurrentUser {
//    private static User currentUser;
//
//    public static User current() {
//        return currentUser;
//    }
//
//    public static void setCurrentUser(User user) {
//        currentUser = user;
//    }
//
//    public static void setUserWithDifferentCustomer(User oldUser, Customer newCustomer) {
//        currentUser = new User(oldUser.getUserData(), newCustomer);
//    }
//
////    public static List<Account> fetchAccounts() throws TException {
////        AccountsTapiService.Client accountsClient = ThriftClients.getAccountsClient();
////        return accountsClient.getAccounts(currentUser.getUserData(), currentUser.getCustomer(), OperationType.DFLT);
////    }
//
//    public static UserData createUserDataFromProperties(String userName) {
//        return createUserDataFromAcus(PropertyLoader.loadProperty(userName + ".acus"));
//    }
//
//    public static UserData createUserDataFromAcus(String acus) {
//        return new UserData(acus);
//    }
//
//    public static Customer createCustomerFromProperties(String userName) {
//        return createCustomerFromCus(PropertyLoader.loadProperty(userName + ".cus"));
//    }
//
//    public static Customer createCustomerFromCus(String cus) {
//        return (new Customer()).setEqId(cus);
//    }
//}
