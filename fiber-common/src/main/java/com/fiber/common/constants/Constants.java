package com.fiber.common.constants;

/**
 * @author panyox
 */
public interface Constants {

    String EMPTY_JSON = "{}";

    String SLASH = "/";

    String DOT = ".";

    String HASH_TAG = "#";

    String AT = "@";

    String ZK_ROOT = "/fiber";

    String ZK_SERVICE_ROOT = "/fiber/service";

    String ZK_DUBBO_SERVICE_ROOT = "/fiber/service/dubbo";

    String ZK_FILTER_ROOT = "/fiber/register/filter";

    String VERSION_REGX = "^v\\d*\\.?\\d*$";

    String FIBER_ERROR = "fiberError";

    String FIBER_CONTENT = "fiberContent";

    String RPC_TYPE = "RPC";

    String SIGN_NAME = "sign";

    String FIBER_CONTEXT = "fiberContext";

    String ROUTE_DATA = "routeData";

    String SERVICE_DATA = "serviceData";

    String BODY_DATA = "bodyData";

    String FORM_DATA = "formData";

    String QUERY_DATA = "queryData";

    String DEFAULT_VERSION = "v1";

}
