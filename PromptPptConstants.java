package com.ibm.promptppt.constants;

public class PromptPptConstants {
	
	private PromptPptConstants() {}

	public static final String USER_ACTIVE_FLAG = "Y";
	public static final String USER_INACTIVE_FLAG = "N";
	
	public static final String TOKEN_GENERATION_URL = "https://iam.cloud.ibm.com/identity/token";
	public static final String TOKEN_GENERATION_APIKEY = "WnD-PBJ0uqNMa0W-svLDwcQ-sxyNv96SIrXP_0NZ0Uot";
	public static final String TOKEN_GENERATION_TYPE = "urn:ibm:params:oauth:grant-type:apikey";
	
	public static final String url = "https://us-south.ml.cloud.ibm.com/ml/v1/text/generation?version=";
	public static final String project_id = "efceb354-a35e-4fc3-9a47-43e51dfa0845";
	public static final String model_id = "meta-llama/llama-3-8b-instruct";
	public static final String decoding_method = "greedy";
	public static final int min_new_tokens = 1;
	public static final int max_new_tokens = 2700;
	public static final double beam_width = 1.0;
	public static final String stop_sequence = "[]";
	public static final String version = "2023-05-29";
	
	public static final String AUTHORIZATION = "Authorization";
	public static final String BEARER = "Bearer ";
	
	public static final String context = "Power Point Presentation";
}

/*
"model_id": "meta-llama/llama-3-8b-instruct",
 "project_id": "efceb354-a35e-4fc3-9a47-43e51dfa0845"
 
 project_id = "bb3cb271-9d33-4615-b10a-79e246eb8434";
	model_id = "ibm/granite-13b-chat-v2";
*/