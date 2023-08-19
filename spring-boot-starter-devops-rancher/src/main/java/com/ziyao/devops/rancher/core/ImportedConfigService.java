package com.ziyao.devops.rancher.core;

import com.ziyao.devops.rancher.api.ImportedConfig;
import com.ziyao.devops.rancher.base.Filters;
import com.ziyao.devops.rancher.base.TypeCollection;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author zhangziyao
 * @since 2021/10/13
 */
public interface ImportedConfigService {

    @GET("importedConfig")
    Call<TypeCollection<ImportedConfig>> list();

    @GET("importedConfig")
    Call<TypeCollection<ImportedConfig>> list(@QueryMap Filters filters);

    @GET("importedConfig/{id}")
    Call<ImportedConfig> get(@Path("id") String id);

    @POST("importedConfig")
    Call<ImportedConfig> create(@Body ImportedConfig importedConfig);

    @PUT("importedConfig/{id}")
    Call<ImportedConfig> update(@Path("id") String id, @Body ImportedConfig importedConfig);

    @DELETE("importedConfig/{id}")
    Call<ResponseBody> delete(@Path("id") String id);

}
