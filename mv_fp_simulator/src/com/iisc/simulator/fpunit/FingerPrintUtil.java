package com.iisc.simulator.fpunit;

public class FingerPrintUtil {

	// used for image
	// for digital persona with kit
	// public static final int FP_IMAGE_WIDTH = 500;
	// public static final int FP_IMAGE_HEIGHT = 500;
	// for verifinger with kit
	public static final int FP_IMAGE_WIDTH = 323;
	public static final int FP_IMAGE_HEIGHT = 352;

	// Used by template
	// be carefull the size of the array must always be 1 larger than a number
	// divisable by 4
	public static final int FP_TEMPLATE_MAX_SIZE = 601;

	// used for matching
	// the max distance between to points when comparing two points to count as
	// a match
	public static final int FP_MATCH_POINT_DISTANCE_MOVEMENT = 10;
	// the max rotation to use when comparint two points to count as a match
	public static final int FP_MATCH_POINT_ROTATION_MOVEMENT = 10;// 10;
	// a percentage
	public static final int FP_MATCH_THRESHOLD = 55;

	// finger print classifications
	// Wirbel class
	public static final int FP_CLASS_WHORL = 1;
	// lasso class
	public static final int FP_CLASS_LEFT_LOOP = 2;
	public static final int FP_CLASS_RIGHT_LOOP = 3;
	public static final int FP_CLASS_ARCH = 4;
	public static final int FP_CLASS_ARCH_TENTED = 5;

	// fingerprint template values
	public static final int FP_TEMPLATE_SIZE = 0;
	public static final int FP_TEMPLATE_ORIGIN_X = 1;
	public static final int FP_TEMPLATE_ORIGIN_Y = 2;
	public static final int FP_TEMPLATE_FEATURE_SIZE = 6;
	public static final int FP_TEMPLATE_SEARCH_RADIUS = 1;

}
