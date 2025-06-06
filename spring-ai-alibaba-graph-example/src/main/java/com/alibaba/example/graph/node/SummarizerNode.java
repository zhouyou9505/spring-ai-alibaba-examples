/*
 * Copyright 2025-2026 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author yHong
 */

package com.alibaba.example.graph.node;

import com.alibaba.cloud.ai.graph.OverAllState;
import com.alibaba.cloud.ai.graph.action.NodeAction;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yHong
 * @version 1.0
 * @since 2025/4/24 15:55
 */
public class SummarizerNode implements NodeAction {

	private final ChatClient chatClient;

	public SummarizerNode(ChatClient chatClient) {
		this.chatClient = chatClient;
	}

	@Override
	public Map<String, Object> apply(OverAllState state) {
		String text = (String) state.value("original_text").orElse("");
		String prompt = "请对以下中文文本进行简洁明了的摘要：\n\n" + text;

		ChatResponse response = chatClient.prompt(prompt).call().chatResponse();
		String summary = response.getResult().getOutput().getText();

		Map<String, Object> result = new HashMap<>();
		result.put("summary", summary);
		return result;
	}

}
