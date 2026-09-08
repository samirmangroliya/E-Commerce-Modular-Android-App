package com.samir.network;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class NetworkModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
  private final Provider<NetworkConfig> networkConfigProvider;

  private final Provider<AuthInterceptor> authInterceptorProvider;

  private NetworkModule_ProvideOkHttpClientFactory(Provider<NetworkConfig> networkConfigProvider,
      Provider<AuthInterceptor> authInterceptorProvider) {
    this.networkConfigProvider = networkConfigProvider;
    this.authInterceptorProvider = authInterceptorProvider;
  }

  @Override
  public OkHttpClient get() {
    return provideOkHttpClient(networkConfigProvider.get(), authInterceptorProvider.get());
  }

  public static NetworkModule_ProvideOkHttpClientFactory create(
      Provider<NetworkConfig> networkConfigProvider,
      Provider<AuthInterceptor> authInterceptorProvider) {
    return new NetworkModule_ProvideOkHttpClientFactory(networkConfigProvider, authInterceptorProvider);
  }

  public static OkHttpClient provideOkHttpClient(NetworkConfig networkConfig,
      AuthInterceptor authInterceptor) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOkHttpClient(networkConfig, authInterceptor));
  }
}
