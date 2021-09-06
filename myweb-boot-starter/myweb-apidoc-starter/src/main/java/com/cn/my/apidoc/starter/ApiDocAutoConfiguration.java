package com.cn.my.apidoc.starter;


/**
 * @author Bryan Wang
 * @date 2020/1/10-19:54
 * @DescriptionO
 */
//@Configuration
//@EnableConfigurationProperties(com.d3.dpd.cloud.apidoc.ApiDocConfiguration.class)
//@EnableSwagger2
public class ApiDocAutoConfiguration {

//    @Bean
//    public Docket createRestApi(com.d3.dpd.cloud.apidoc.ApiDocConfiguration apiDocConfig) {
//
//        List<String> packages = apiDocConfig.getPackages();
//        List<Predicate<RequestHandler>> packagePredicates = packages.stream().map(RequestHandlerSelectors::basePackage)
//                .collect(Collectors.toList());
//
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo(apiDocConfig))
//                //默认生成返回码
//                .useDefaultResponseMessages(false)
//                .select()
//                //限制包范围
//                    .apis(Predicates.or(packagePredicates))
//                .build();
//    }
//
//    private ApiInfo apiInfo(com.d3.dpd.cloud.apidoc.ApiDocConfiguration apiDocConfig) {
//        return new ApiInfoBuilder()
//                .title(apiDocConfig.getTitle())
//                .description(apiDocConfig.getDescription())
//                .version(apiDocConfig.getVersion())
//                .build();
//    }
//
//    @Configuration
//    public static class WebMvcConfig implements WebMvcConfigurer {
//        @Override
//        public void addResourceHandlers(ResourceHandlerRegistry registry) {
//            registry.addResourceHandler("/swagger-ui.html")
//                    .addResourceLocations("classpath:/META-INF/resources/");
//            registry.addResourceHandler("/webjars/**")
//                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
//
//        }
//    }
}
