import * as anvilSelectors from "layoutSystems/anvil/integrations/selectors";
import { mockGetIsAnvilLayout } from "test/testCommon";
import WidgetFactory from "WidgetProvider/factory";
import { BlueprintOperationTypes } from "WidgetProvider/constants";

import type { BlueprintOperation } from "./WidgetBlueprintSagas";
import { executeWidgetBlueprintChildOperations } from "./WidgetBlueprintSagas";

jest
  .spyOn(anvilSelectors, "getIsAnvilLayout")
  .mockImplementation(mockGetIsAnvilLayout);
describe("WidgetBlueprintSagas", () => {
  it("should returns widgets after executing the child operation", async () => {
    const mockBlueprintChildOperation: BlueprintOperation = {
      type: BlueprintOperationTypes.CHILD_OPERATIONS,
      fn: () => {
        return { widgets: {} };
      },
    };

    jest
      .spyOn(WidgetFactory, "getWidgetDefaultPropertiesMap")
      .mockReturnValue({});

    const generator = executeWidgetBlueprintChildOperations(
      mockBlueprintChildOperation,
      {
        widgetId: {
          image: "",
          defaultImage: "",
          widgetId: "Widget1",
          type: "LIST_WIDGET",
          widgetName: "List1",
          parentId: "parentId",
          renderMode: "CANVAS",
          parentColumnSpace: 2,
          parentRowSpace: 3,
          leftColumn: 2,
          rightColumn: 3,
          topRow: 1,
          bottomRow: 3,
          isLoading: false,
          items: [],
          version: 16,
          disablePropertyPane: false,
        },
      },
      ["widgetId"],
      "parentId",
    );

    generator.next();
    expect(generator.next().value).toStrictEqual({});
  });
});
