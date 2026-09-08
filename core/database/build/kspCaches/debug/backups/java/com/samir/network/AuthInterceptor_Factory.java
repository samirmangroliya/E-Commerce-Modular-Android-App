package com.samir.network;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class AuthInterceptor_Factory implements Factory<AuthInterceptor> {
  private final Provider<NetworkConfig> networkConfigProvider;

  private AuthInterceptor_Factory(Provider<NetworkConfig> networkConfigProvider) {
    this.networkConfigProvider = networkConfigProvider;
  }

  @Override
  public AuthInterceptor get() {
    return newInstance(networkConfigProvider.get());
  }

  public static AuthInterceptor_Factory create(Provider<NetworkConfig> networkConfigProvider) {
    return new AuthInterceptor_Factory(networkConfigProvider);
  }

  public static AuthInterceptor newInstance(NetworkConfig networkConfig) {
    return new AuthInterceptor(networkConfig);
  }
}
