package com.samir.home.domain;

import com.samir.domain.GetProductsUseCase;
import com.samir.domain.SearchProductsUseCase;
import com.samir.domain.SortProductsUseCase;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<GetProductsUseCase> getProductsUseCaseProvider;

  private final Provider<SearchProductsUseCase> searchProductsUseCaseProvider;

  private final Provider<SortProductsUseCase> sortProductsUseCaseProvider;

  private HomeViewModel_Factory(Provider<GetProductsUseCase> getProductsUseCaseProvider,
      Provider<SearchProductsUseCase> searchProductsUseCaseProvider,
      Provider<SortProductsUseCase> sortProductsUseCaseProvider) {
    this.getProductsUseCaseProvider = getProductsUseCaseProvider;
    this.searchProductsUseCaseProvider = searchProductsUseCaseProvider;
    this.sortProductsUseCaseProvider = sortProductsUseCaseProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(getProductsUseCaseProvider.get(), searchProductsUseCaseProvider.get(), sortProductsUseCaseProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<GetProductsUseCase> getProductsUseCaseProvider,
      Provider<SearchProductsUseCase> searchProductsUseCaseProvider,
      Provider<SortProductsUseCase> sortProductsUseCaseProvider) {
    return new HomeViewModel_Factory(getProductsUseCaseProvider, searchProductsUseCaseProvider, sortProductsUseCaseProvider);
  }

  public static HomeViewModel newInstance(GetProductsUseCase getProductsUseCase,
      SearchProductsUseCase searchProductsUseCase, SortProductsUseCase sortProductsUseCase) {
    return new HomeViewModel(getProductsUseCase, searchProductsUseCase, sortProductsUseCase);
  }
}
