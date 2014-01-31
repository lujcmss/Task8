package task7.databeans;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Example bean for getting data from MySQL database
 */
public class FundGraphBean {

    /**
     * Class for store one result from query
     */
    public static class Report {
       
        private String name;
        private int value;
        private Date period;
        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @return the value
         */
        public int getValue() {
            return value;
        }

        //return the value for the series
		public Date getPeriod() {
			return period;
		}
    }

    private static final String url =
            "jdbc:mysql://localhost/webapp?useUnicode=true&amp;characterEncoding=utf8";
    private static final String dbUser = "root";
    private static final String dbPass = "root";
    private static final String sqlquery =
            "select p.name as name,o.price as value,o.priceDate as period " +
                    "from TASK7_FUND p, TASK7_FUNDPRICEHISTORY o " +
                    "where p.fundId = o.fundBean_fundId";

    public List getReportsList() throws Exception {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, dbUser, dbPass);
            st = conn.createStatement();
            rs = st.executeQuery(sqlquery);
            List res = new ArrayList();
            while (rs.next()) {
                Report r = new Report();
                r.name = rs.getString("name");
                r.value = rs.getInt("value");
                r.period = rs.getDate("period");
                res.add(r);
            }
           
            return res;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }
    }
}