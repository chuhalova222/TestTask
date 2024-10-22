package org.api.models;

import lombok.Data;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.List;

@Data
public class GetEventsResponse {

    public List<Datum> data;
    public int ec;
    public int tec;
    public long lts;
    public List<Integer> acid;
    @Nullable
    public String result;
    public boolean isSuccessfull;
    @Nullable
    public String userInfo;

    @Data
    public static class Bt {
        public int id;
        public String n;
        public int o;
        public int s;
        public List<Odd> odds;
    }
    @Data
    public static class Datum {
        public int id;
        public int st;
        public Date gt;
        public String gn;
        public boolean bk;
        public int et;
        public int o;
        public int cid;
        public int sci;
        public boolean ng;
        public int sid;
        public String sn;
        public int co;
        public String cn;
        public int lid;
        public int lo;
        public String ln;
        public String h;
        public String a;
        public int btc;
        public List<Bt> bts;
        public boolean hco;
    }

    @Data
    public static class Odd {
        public String n;
        public int s;
        public int oc;
        public String p;
        public String l;
        public int or;
        public String id;
    }
}
