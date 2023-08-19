package com.ziyao.devops.rancher.core;

import com.ziyao.devops.rancher.api.ImportYamlOutput;
import com.ziyao.devops.rancher.base.Filters;
import com.ziyao.devops.rancher.base.TypeCollection;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author zhangziyao
 * @since 2021/10/13
 */
public interface ImportYamlOutputService {

    @GET("importYamlOutput")
    Call<TypeCollection<ImportYamlOutput>> list();

    @GET("importYamlOutput")
    Call<TypeCollection<ImportYamlOutput>> list(@QueryMap Filters filters);

    @GET("importYamlOutput/{id}")
    Call<ImportYamlOutput> get(@Path("id") String id);

    @POST("importYamlOutput")
    Call<ImportYamlOutput> create(@Body ImportYamlOutput importYamlOutput);

    @PUT("importYamlOutput/{id}")
    Call<ImportYamlOutput> update(@Path("id") String id, @Body ImportYamlOutput importYamlOutput);

    @DELETE("importYamlOutput/{id}")
    Call<ResponseBody> delete(@Path("id") String id);

}
