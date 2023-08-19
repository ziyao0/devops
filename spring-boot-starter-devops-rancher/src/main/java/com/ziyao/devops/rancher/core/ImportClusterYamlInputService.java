package com.ziyao.devops.rancher.core;

import com.ziyao.devops.rancher.api.ImportClusterYamlInput;
import com.ziyao.devops.rancher.base.Filters;
import com.ziyao.devops.rancher.base.TypeCollection;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author zhangziyao
 * @since 2021/10/13
 */
public interface ImportClusterYamlInputService {

    @GET("importClusterYamlInput")
    Call<TypeCollection<ImportClusterYamlInput>> list();

    @GET("importClusterYamlInput")
    Call<TypeCollection<ImportClusterYamlInput>> list(@QueryMap Filters filters);

    @GET("importClusterYamlInput/{id}")
    Call<ImportClusterYamlInput> get(@Path("id") String id);

    @POST("importClusterYamlInput")
    Call<ImportClusterYamlInput> create(@Body ImportClusterYamlInput importClusterYamlInput);

    @PUT("importClusterYamlInput/{id}")
    Call<ImportClusterYamlInput> update(@Path("id") String id, @Body ImportClusterYamlInput importClusterYamlInput);

    @DELETE("importClusterYamlInput/{id}")
    Call<ResponseBody> delete(@Path("id") String id);

}
