package x.lab;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Properties;

public class LdapTest {
    private static final String returnedVOAtts[] = {"description", "telephoneNumber"};

    private final static String INITIAL_CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
    private final static String SECURITY_AUTHENTICATION = "simple";

    private static String ldapServerIp = "127.0.0.1";
    private static int ldapServerPort = 10389;
    private static String userCd = "uid=admin,ou=system";
    private static String userPwd = "secret";

    static {

    }


    public static void main(String[] args) throws NamingException {
        String adUrl = "ldap://" + ldapServerIp + ":" + ldapServerPort;

        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        env.put(Context.SECURITY_AUTHENTICATION, SECURITY_AUTHENTICATION);
        env.put(Context.SECURITY_PRINCIPAL, userCd);
        env.put(Context.SECURITY_CREDENTIALS, userPwd);
        env.put(Context.PROVIDER_URL, adUrl);

        LdapContext lc = new InitialLdapContext(env, null);

        SearchControls searchCtls = new SearchControls();
        // Specify the search scope
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
//        String searchFilter = "(&(objectCategory=person)(objectClass=user)(userPrincipalName="
//                + userCd + AD_FLAG + cnName + "))";

        String searchFilter = "(&(objectClass=person)(cn=lisi))";

        // Specify the Base for the search, root directory
        String searchBase = "ou=system";

        // set AD user's Attributes,Specify the attributes to return
        searchCtls.setReturningAttributes(returnedVOAtts); // set
        NamingEnumeration<SearchResult> answer = lc.search(searchBase, searchFilter, searchCtls);

        StringBuffer user = new StringBuffer();
        SearchResult sr = answer.next();
        Attributes attrs = sr.getAttributes();
        if (attrs != null) {
            for (NamingEnumeration<?> ae = attrs.getAll(); ae.hasMore(); ) {
                Attribute attr = (Attribute) ae.next();
                for (NamingEnumeration<?> e = attr.getAll(); e.hasMore(); ) {
                    String value = (String) e.next();
                    user.append(attr.getID()).append(":").append(value).append(";");
                    break;
                }
            }
        }

        System.out.println(user);
    }
}
