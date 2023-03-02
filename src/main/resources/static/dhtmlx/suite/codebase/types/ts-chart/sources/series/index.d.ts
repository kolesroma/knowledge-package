import area from "./Area";
import bar from "./Bar";
import xbar from "./BarX";
import donut from "./Donut";
import line from "./Line";
import pie from "./Pie";
import pie3D from "./Pie3D";
import radar from "./Radar";
import scatter from "./Scatter";
import spline from "./Spline";
import splineArea from "./SplineArea";
import treeMap from "./TreeMap";
import calendarHeatMap from "./CalendarHeatMap";
declare const seriesTypes: {
    line: typeof line;
    spline: typeof spline;
    area: typeof area;
    splineArea: typeof splineArea;
    scatter: typeof scatter;
    pie: typeof pie;
    pie3D: typeof pie3D;
    donut: typeof donut;
    radar: typeof radar;
    bar: typeof bar;
    xbar: typeof xbar;
    treeMap: typeof treeMap;
    calendarHeatMap: typeof calendarHeatMap;
};
export default seriesTypes;
