package utils;

import lombok.SneakyThrows;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransportException;
import ru.alfabank.tests.core.helpers.PropertyLoader;
import ru.alfabank.thrift.accounts.AccountsTapiService;
import ru.alfabank.thrift.catalogs.CatalogsApiService;
import ru.alfabank.thrift.organizations.OrganizationsService;
import ru.alfabank.thrift.payments.PaymentsApiService;
import ru.alfabank.thrift.transactions.TransactionsApiService;

public class ThriftClients {

    private static AccountsTapiService.Client accountsClient;
    private static TransactionsApiService.Client transactionsClient;
    private static PaymentsApiService.Client paymentsClient;
    private static OrganizationsService.Client organizationsClient;
    private static CatalogsApiService.Client catalogsClient;

    @SneakyThrows
    public static AccountsTapiService.Client getAccountsClient() {
        if (accountsClient != null) {
            return accountsClient;
        }
        TProtocol protocol = getProtocolFromUrl("accountsApiUrl");
        accountsClient = new AccountsTapiService.Client(protocol);
        return accountsClient;
    }

    @SneakyThrows
    public static TransactionsApiService.Client getTransactionsClient() {
        if (transactionsClient != null) {
            return transactionsClient;
        }
        TProtocol protocol = getProtocolFromUrl("transactionsApiUrl");
        transactionsClient = new TransactionsApiService.Client(protocol);
        return transactionsClient;
    }

    @SneakyThrows
    public static PaymentsApiService.Client getPaymentsClient() {
        if (paymentsClient != null) {
            return paymentsClient;
        }
        TProtocol protocol = getProtocolFromUrl("paymentsApiUrl");
        paymentsClient = new PaymentsApiService.Client(protocol);
        return paymentsClient;
    }

    @SneakyThrows
    public static OrganizationsService.Client getOrganizationsClient() {
        if (organizationsClient != null) {
            return organizationsClient;
        }
        TProtocol protocol = getProtocolFromUrl("organizationsApiUrl");
        organizationsClient = new OrganizationsService.Client(protocol);
        return organizationsClient;
    }

    @SneakyThrows
    public static CatalogsApiService.Client getCatalogsClient() {
        if (catalogsClient != null) {
            return catalogsClient;
        }
        TProtocol protocol = getProtocolFromUrl("catalogsApiUrl");
        catalogsClient = new CatalogsApiService.Client(protocol);
        return catalogsClient;
    }

    private static TProtocol getProtocolFromUrl(String urlProperty) throws TTransportException {
        String url = PropertyLoader.loadProperty(urlProperty);
        THttpClient httpClient = new THttpClient(url);
        return new TBinaryProtocol(httpClient);
    }
}
