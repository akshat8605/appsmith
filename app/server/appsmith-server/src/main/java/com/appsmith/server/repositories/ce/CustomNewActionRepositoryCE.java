package com.appsmith.server.repositories.ce;

import com.appsmith.external.models.CreatorContextType;
import com.appsmith.server.acl.AclPermission;
import com.appsmith.server.domains.NewAction;
import com.appsmith.server.dtos.PluginTypeAndCountDTO;
import com.appsmith.server.repositories.AppsmithRepository;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CustomNewActionRepositoryCE extends AppsmithRepository<NewAction> {

    Flux<NewAction> findByApplicationId(String applicationId, AclPermission aclPermission);

    Mono<NewAction> findByUnpublishedNameAndPageId(String name, String pageId, AclPermission aclPermission);

    Flux<NewAction> findByPageId(String pageId, AclPermission aclPermission);

    Flux<NewAction> findByPageId(String pageId, Optional<AclPermission> aclPermission);

    Flux<NewAction> findByPageId(String pageId);

    Flux<NewAction> findByPageIdAndViewMode(String pageId, Boolean viewMode, AclPermission aclPermission);

    Flux<NewAction> findUnpublishedActionsByNameInAndPageId(Set<String> names, String pageId, AclPermission permission);

    Flux<NewAction> findUnpublishedActionsByPageIdAndExecuteOnLoadSetByUserTrue(
            String pageId, AclPermission permission);

    Flux<NewAction> findAllActionsByNameAndPageIdsAndViewMode(
            String name, List<String> pageIds, Boolean viewMode, AclPermission aclPermission, Sort sort);

    Flux<NewAction> findByApplicationId(String applicationId, AclPermission aclPermission, Sort sort);

    Flux<NewAction> findByApplicationId(
            String applicationId, Optional<AclPermission> aclPermission, Optional<Sort> sort);

    Flux<NewAction> findByApplicationIdAndViewMode(String applicationId, Boolean viewMode, AclPermission aclPermission);

    Mono<Long> countByDatasourceId(String datasourceId);

    Mono<NewAction> findByBranchNameAndDefaultActionId(
            String branchName, String defaultActionId, Boolean viewMode, AclPermission permission);

    Flux<NewAction> findByDefaultApplicationId(String defaultApplicationId, Optional<AclPermission> permission);

    Flux<NewAction> findByPageIds(List<String> pageIds, AclPermission permission);

    Flux<NewAction> findByPageIds(List<String> pageIds, Optional<AclPermission> permission);

    Flux<NewAction> findNonJsActionsByApplicationIdAndViewMode(
            String applicationId, Boolean viewMode, AclPermission aclPermission);

    Flux<NewAction> findAllNonJsActionsByNameAndPageIdsAndViewMode(
            String name, List<String> pageIds, Boolean viewMode, AclPermission aclPermission, Sort sort);

    Mono<Void> publishActions(String applicationId, AclPermission permission);

    Mono<Integer> archiveDeletedUnpublishedActions(String applicationId, AclPermission permission);

    Flux<PluginTypeAndCountDTO> countActionsByPluginType(String applicationId);

    Flux<NewAction> findAllByApplicationIdsWithoutPermission(List<String> applicationIds, List<String> includeFields);

    Flux<NewAction> findAllUnpublishedActionsByContextIdAndContextType(
            String contextId, CreatorContextType contextType, AclPermission permission, boolean includeJs);

    Flux<NewAction> findAllPublishedActionsByContextIdAndContextType(
            String contextId, CreatorContextType contextType, AclPermission permission, boolean includeJs);
}
