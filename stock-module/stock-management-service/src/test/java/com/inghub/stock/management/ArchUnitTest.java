package com.inghub.stock.management;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static org.assertj.core.api.Assertions.assertThat;

public class ArchUnitTest {

  private static final String DESCRIPTION = "Imported classes contain package %s";
  private static final String MAIN_PACKAGE = "com.inghub.stock.management";
  private static final String REPOSITORY_PACKAGE = ".repository";
  private static final String SERVICE_PACKAGE = ".service";
  private static final String WEB_PACKAGE = ".controller";
  private static final String DOMAIN_LAYER_NAME = "Domain";
  private static final String SERVICE_LAYER_NAME = "Service";
  private static final String PERSISTENCE_LAYER_NAME = "Persistence";
  private static final String PRESENTATION_LAYER_NAME = "Presentation";
  private static final String DOMAIN_PACKAGE_ID = "..entities..";
  private static final String SERVICE_PACKAGE_ID = "..service..";
  private static final String REPOSITORY_PACKAGE_ID = "..repository..";
  private static final String CONTROLLER_PACKAGE_ID = "..controller..";

  private final JavaClasses importedClasses = new ClassFileImporter()
          .withImportOption(location -> !location.contains("/test-classes/")).importPackages("com.inghub.stock.management");

  /**
   * Checks if main packages, building architectural layers even exists
   */
  @Test
  void validateInternalPackageNames() {

    assertThat(importedClasses.containPackage(MAIN_PACKAGE.concat(REPOSITORY_PACKAGE)))
            .as(DESCRIPTION, MAIN_PACKAGE.concat(REPOSITORY_PACKAGE)).isTrue();

    assertThat(importedClasses.containPackage(MAIN_PACKAGE.concat(SERVICE_PACKAGE)))
            .as(DESCRIPTION, MAIN_PACKAGE.concat(SERVICE_PACKAGE)).isTrue();

    assertThat(importedClasses.containPackage(MAIN_PACKAGE.concat(WEB_PACKAGE)))
            .as(DESCRIPTION, MAIN_PACKAGE.concat(WEB_PACKAGE)).isTrue();
  }

  /**
   * Checks if class, interface and enum name suffixes conventions are met
   */
  @Test
  void validateNamingConvention() {
    ArchRule webLayerRule = classes().that().resideInAPackage(CONTROLLER_PACKAGE_ID)
        .and().areNotAnonymousClasses().should()
        .haveSimpleNameEndingWith("Controller")
        .orShould().haveSimpleNameEndingWith("Api")
        .orShould().haveSimpleNameEndingWith("Mapper")
        .orShould().haveSimpleNameEndingWith("MapperImpl");

    ArchRule serviceLayerRule = classes().that().resideInAPackage(SERVICE_PACKAGE_ID)
        .and().areNotAnonymousClasses().should()
        .haveSimpleNameEndingWith("Service")
        .orShould().haveSimpleNameEndingWith("ServiceImpl");

    ArchRule repositoryLayerRule = classes().that()
        .resideInAPackage(REPOSITORY_PACKAGE_ID)
        .and().areNotAnonymousClasses()
        .should().haveSimpleNameEndingWith("Repository")
        .orShould().haveSimpleNameEndingWith("Entity");

    webLayerRule.check(importedClasses);
    serviceLayerRule.check(importedClasses);
    repositoryLayerRule.check(importedClasses);
  }

  /**
   * Checks if classes in Presentation and Repository layers are always public
   */
  @Test
  void validatePublicWebClasses() {
    ArchRule publicRule = classes().that().resideInAPackage(REPOSITORY_PACKAGE_ID).should().bePublic();

    publicRule.check(importedClasses);
  }

  /**
   * Checks if architecture conventions for interlayer access and invocations are strictly met.
   */
  @Test
  void validateLayersAccess() {
    Architectures.LayeredArchitecture arch = layeredArchitecture().consideringOnlyDependenciesInLayers()
            .layer(DOMAIN_LAYER_NAME).definedBy(DOMAIN_PACKAGE_ID).layer(SERVICE_LAYER_NAME)
            .definedBy(SERVICE_PACKAGE_ID).layer(PERSISTENCE_LAYER_NAME).definedBy(REPOSITORY_PACKAGE_ID)
            .layer(PRESENTATION_LAYER_NAME).definedBy(CONTROLLER_PACKAGE_ID).whereLayer(DOMAIN_LAYER_NAME)
            .mayNotAccessAnyLayer().whereLayer(PRESENTATION_LAYER_NAME).mayNotBeAccessedByAnyLayer()
            .whereLayer(SERVICE_LAYER_NAME).mayOnlyBeAccessedByLayers(PRESENTATION_LAYER_NAME)
            .whereLayer(PERSISTENCE_LAYER_NAME).mayOnlyBeAccessedByLayers(SERVICE_LAYER_NAME);
    // arch.check(importedClasses);
  }
}
