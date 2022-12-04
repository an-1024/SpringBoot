/*
 * Copyright 2002-2020 the original author or authors.
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
 */

package org.springframework.context.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.OrderComparator;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.lang.Nullable;

/**
 * Delegate for AbstractApplicationContext's post-processor handling.
 *
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @since 4.0
 */
final class PostProcessorRegistrationDelegate {

	private PostProcessorRegistrationDelegate() {
	}


	public static void invokeBeanFactoryPostProcessors(
			ConfigurableListableBeanFactory beanFactory, List<BeanFactoryPostProcessor> beanFactoryPostProcessors) {

		// Invoke BeanDefinitionRegistryPostProcessors first, if any.
		// 无论什么情况，优先执行 BeanDefinitionRegistryPostProcessors，将已经执行过的 BeanFactoryPostProcessor
		// 存储在 processedBeans 中，防止重复执行,hashSet 去重
		Set<String> processedBeans = new HashSet<>();

		// 判断 beanFactory 是否是 BeanDefinitionRegistry 类型，此处的 beanFactory 是 DefaultListableBeanFactory, 实现了 BeanDefinitionRegistry
		// 接口，为 true
		if (beanFactory instanceof BeanDefinitionRegistry) {
			// 将 beanFactory 转换为 BeanDefinitionRegistry
			BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
			// BeanFactoryPostProcessor 集合 regularPostProcessors
			List<BeanFactoryPostProcessor> regularPostProcessors = new ArrayList<>();
			// BeanDefinitionRegistryPostProcessor 集合 registryProcessors
			List<BeanDefinitionRegistryPostProcessor> registryProcessors = new ArrayList<>();
			// 此处需要做一个区分：两个接口是不同的：需要区分 BeanDefinitionRegistryPostProcessor 操作 beandefinition
			// 和 BeanFactoryPostProcessor 操作 beanFactory，前者是后者的子集
			// 遍历 beanFactoryPostProcessors 集合
			for (BeanFactoryPostProcessor postProcessor : beanFactoryPostProcessors) {
				// 对 BeanFactoryPostProcessor 和 BeanDefinitionRegistryPostProcessor 接口进行区分，分别放入不同的集合
				// 如果处理器属于 BeanDefinitionRegistryPostProcessor 获取手动添加的处理器，需要明白的是这个处理器并没有放入
				// spring 的集合中
				if (postProcessor instanceof BeanDefinitionRegistryPostProcessor) {
					BeanDefinitionRegistryPostProcessor registryProcessor =
							(BeanDefinitionRegistryPostProcessor) postProcessor;
					// 先执行子类的  postProcessBeanDefinitionRegistry 方法
					registryProcessor.postProcessBeanDefinitionRegistry(registry);
					// 将 registryProcessor 添加到 BeanDefinitionRegistryPostProcessor 的集合中用于后续执行
					// postBeanFactoryProcess 方法
					registryProcessors.add(registryProcessor);
				}
				else { // 否则添加 BeanFactoryPostProcessor 的集合中，用于后续执行 postBeanFactoryProcess 方法
					regularPostProcessors.add(postProcessor);
				}
			}

			// Do not initialize FactoryBeans here: We need to leave all regular beans
			// uninitialized to let the bean factory post-processors apply to them!
			// Separate between BeanDefinitionRegistryPostProcessors that implement
			// PriorityOrdered, Ordered, and the rest.
			// 用于保存当前 BeanDefinitionRegistryPostProcessor 集合，即子类的集合处理完成
			List<BeanDefinitionRegistryPostProcessor> currentRegistryProcessors = new ArrayList<>();

			// First, invoke the BeanDefinitionRegistryPostProcessors that implement PriorityOrdered.
			// 根据类型 BeanDefinitionRegistryPostProcessor 获取 bean 的名称
			// registryProcessor.postProcessBeanDefinitionRegistry(registry); 中注册了新的 BeanDefinitionRegistryPostProcessor 处理器
			// 通过 beanFactory.getBeanNamesForType 根据类型获取实现 BDPP 的处理器，以及在 XML 中配置指定的处理器
			String[] postProcessorNames =
					beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
			// 将 BeanDefinitionRegistryPostProcessor 放入当前的执行集合
			for (String ppName : postProcessorNames) {
				// 判断接口类型是否符合PriorityOrdered类
				if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
					// 获取名称对应的 bean 实例，添加到 currentRegistryProcessors 中
					currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
					// 将已经执行的 beanFactoryProcess 添加到已经执行的集合中
					processedBeans.add(ppName);
				}
			}
			// 按照优先级顺序操作
			sortPostProcessors(currentRegistryProcessors, beanFactory);
			// 将当前执行的集合放入 BeanDefinitionRegistryPosProcess 集合中
			registryProcessors.addAll(currentRegistryProcessors);
			// 遍历 currentRegistryProcessors 集合执行 postProcessBeanDefinitionRegistry
			invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
			// 执行完成后，清空当前集合
			currentRegistryProcessors.clear();

			// Next, invoke the BeanDefinitionRegistryPostProcessors that implement Ordered.
			// 再次获取值的原因：在 invokeBeanDefinitionRegistryPostProcessors 执行 postProcessBeanDefinitionRegistry 的时候存在添加
			// 其他实现 BeanDefinitionRegistryPostProcessor 的接口，因此需要重新获取
			postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
			// 将 BeanDefinitionRegistryPostProcessor 放入当前的执行集合
			for (String ppName : postProcessorNames) {
				// 判断接口类型是否符合 Ordered 类
				if (!processedBeans.contains(ppName) && beanFactory.isTypeMatch(ppName, Ordered.class)) {
					// 获取名称对应的 bean 实例，添加到 currentRegistryProcessors 中
					currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
					// 将已经执行的 beanFactoryProcess 添加到已经执行的集合中
					processedBeans.add(ppName);
				}
			}
			// 按照优先级顺序操作
			sortPostProcessors(currentRegistryProcessors, beanFactory);
			// 将当前执行的集合放入 BeanDefinitionRegistryPosProcess 集合中
			registryProcessors.addAll(currentRegistryProcessors);
			// 遍历 currentRegistryProcessors 集合执行 postProcessBeanDefinitionRegistry
			invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
			// 执行完成后，清空当前集合
			currentRegistryProcessors.clear();

			// Finally, invoke all other BeanDefinitionRegistryPostProcessors until no further ones appear.
			// 循环处理实现 BeanDefinitionRegistryPostProcessor 接口的类，防止前面有遗漏的处理器没有执行，有遗漏的原因是因为实现处理器
			// 的接口类没有按照 PriorityOrdered -》 Ordered 的顺序注入，比如在实现 Ordered 的接口类中注册实现 PriorityOrdered 的接口类
			boolean reiterate = true;
			while (reiterate) {
				// 循环标志位
				reiterate = false;
				// 再次根据类型 BeanDefinitionRegistryPostProcessor 获取 bean 的名称，这里只要实现了 BeanDefinitionRegistryPostProcessor
				// 接口就可以将遗漏的获取到执行
				postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
				for (String ppName : postProcessorNames) {
					// 如果集合不包含 bean 的名称将这个 bean放入 currentRegistryProcessors 集合中
					if (!processedBeans.contains(ppName)) {
						// 获取名称对应的 bean 实例，添加到 currentRegistryProcessors 中
						currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
						// 将已经执行的 beanFactoryProcess 添加到已经执行的集合中
						processedBeans.add(ppName);
						reiterate = true;
					}
				}
				// 按照优先级顺序操作
				sortPostProcessors(currentRegistryProcessors, beanFactory);
				// 将当前执行的集合放入 BeanDefinitionRegistryPosProcess 集合中
				registryProcessors.addAll(currentRegistryProcessors);
				// 遍历 currentRegistryProcessors 集合执行 postProcessBeanDefinitionRegistry
				invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
				// 清空 currentRegistryProcessors 集合
				currentRegistryProcessors.clear();
			}

			// Now, invoke the postProcessBeanFactory callback of all processors handled so far.
			// 分别处理两个集合中 BeanFactoryPostProcessor 集合 regularPostProcessors 和 BeanDefinitionRegistryPostProcessor 集合 registryProcessors
			// 的回调
			// 执行子接口 BeanDefinitionRegistryPostProcessor 的 postProcessBeanFactory 方法
			invokeBeanFactoryPostProcessors(registryProcessors, beanFactory);
			// 执行 BeanFactoryPostProcessor 的 postProcessBeanFactory 方法
			invokeBeanFactoryPostProcessors(regularPostProcessors, beanFactory);
		}

		else {
			// Invoke factory processors registered with the context instance.
			// 否则处理 BeanFactory 的类
			invokeBeanFactoryPostProcessors(beanFactoryPostProcessors, beanFactory);
		}

		// 到目目前为止，上述一整段代码都是处理实现了 beanFactoryPostProcessors 接口和容器中所有实现了 BeanDefinitionRegistorypostProcessor 接口的类，
		// 以及 用户添加的实现 BeanFactoryPostprocessor 接口类，
		// 接着开始处理容器中由 Spring 根据类型获取所有实现 BeanFactoryPostProcessor 的接口类。这些类包含了只实现了 BeanFactoryPostProcessor 接口，
		// 没有实现 BeanDefinitionRegistryPostProcessor 接口；

		// Do not initialize FactoryBeans here: We need to leave all regular beans
		// uninitialized to let the bean factory post-processors apply to them!
		// 找到所有实现 BeanFactoryPostProcessor 的接口类，通过类型获取，以配置文件 XML 为例，获取的类型就是在 XML 配置的
		// 接口类，前面执行的代码没有处理通过类型获取的接口类
		String[] postProcessorNames =
				beanFactory.getBeanNamesForType(BeanFactoryPostProcessor.class, true, false);

		// Separate between BeanFactoryPostProcessors that implement PriorityOrdered,
		// Ordered, and the rest.
		// 用于存放实现 PriorityOrdered 接口的 BeanFactoryPostProcessor
		List<BeanFactoryPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();
		// 用于存放实现 Ordered 接口的 BeanFactoryPostProcessor 接口
		List<String> orderedPostProcessorNames = new ArrayList<>();
		// 用于存放没有实现 PriorityOrdered、Ordered 接口的 BeanFactoryPostProcessor 接口
		List<String> nonOrderedPostProcessorNames = new ArrayList<>();
		for (String ppName : postProcessorNames) {
			if (processedBeans.contains(ppName)) {
				// skip - already processed in first phase above
			}
			else if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
				// 向 priorityOrderedPostProcessors 集合中添加接口
				priorityOrderedPostProcessors.add(beanFactory.getBean(ppName, BeanFactoryPostProcessor.class));
			}
			else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
				// 向 orderedPostProcessorNames 集合中添加接口
				orderedPostProcessorNames.add(ppName);
			}
			else {
				// 向 nonOrderedPostProcessorNames 集合中添加接口
				nonOrderedPostProcessorNames.add(ppName);
			}
		}

		// First, invoke the BeanFactoryPostProcessors that implement PriorityOrdered.
		// 对实现 PriorityOrdered 接口排序
		sortPostProcessors(priorityOrderedPostProcessors, beanFactory);
		// 执行 postProcessBeanFactory
		invokeBeanFactoryPostProcessors(priorityOrderedPostProcessors, beanFactory);

		// Next, invoke the BeanFactoryPostProcessors that implement Ordered.
		List<BeanFactoryPostProcessor> orderedPostProcessors = new ArrayList<>(orderedPostProcessorNames.size());
		for (String postProcessorName : orderedPostProcessorNames) {
			// 添加实现了 Ordered 接口的类
			orderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
		}
		// 对集合排序
		sortPostProcessors(orderedPostProcessors, beanFactory);
		// 执行 postProcessBeanFactory
		invokeBeanFactoryPostProcessors(orderedPostProcessors, beanFactory);

		// Finally, invoke all other BeanFactoryPostProcessors.
		List<BeanFactoryPostProcessor> nonOrderedPostProcessors = new ArrayList<>(nonOrderedPostProcessorNames.size());
		for (String postProcessorName : nonOrderedPostProcessorNames) {
			// 添加实现了普通的 BeanFactoryPostProcessor 接口类
			nonOrderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
		}
		// 执行 postProcessBeanFactory
		invokeBeanFactoryPostProcessors(nonOrderedPostProcessors, beanFactory);

		// Clear cached merged bean definitions since the post-processors might have
		// modified the original metadata, e.g. replacing placeholders in values...
		// 清除元数据缓存(mergerBeanDefinitions、aliBeannameType\singletonBeanNameType)
		beanFactory.clearMetadataCache();
	}

	public static void registerBeanPostProcessors(
			ConfigurableListableBeanFactory beanFactory, AbstractApplicationContext applicationContext) {
		// 找到所有实现了 BeanPostProcessor 接口的类
		String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);

		// Register BeanPostProcessorChecker that logs an info message when
		// a bean is created during BeanPostProcessor instantiation, i.e. when
		// a bean is not eligible for getting processed by all BeanPostProcessors.
		// 记录下 BeanPostProcessor 的目标计数，加 1 的原因是下面一段代码添加了一个 BeanPostProcessorChecker，所以计数要 +1
		int beanProcessorTargetCount = beanFactory.getBeanPostProcessorCount() + 1 + postProcessorNames.length;
		// 添加 BeanPostProcessorChecker (主要用于记录信息) 到 beanFactory 中
		beanFactory.addBeanPostProcessor(new BeanPostProcessorChecker(beanFactory, beanProcessorTargetCount));

		// Separate between BeanPostProcessors that implement PriorityOrdered,
		// Ordered, and the rest.
		// 定义存放实现了 PriorityOrdered 接口的 BeanPostProcessor 集合
		List<BeanPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();
		// 定义存放 spring 内部的 BeanPostProcessor
		List<BeanPostProcessor> internalPostProcessors = new ArrayList<>();
		// 定义存放实现了 Ordered 接口的 BeanPostProcessor 的 bean 名称的集合
		List<String> orderedPostProcessorNames = new ArrayList<>();
		// 定义存放普通的 BeanPostProcessor 的 bean 名称的集合
		List<String> nonOrderedPostProcessorNames = new ArrayList<>();
		// 遍历实现了 BeanPostProcessor 接口的 bean
		for (String ppName : postProcessorNames) {
			// 如果该 bean 的类型属于 PriorityOrdered
			if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
				// 获取该 bean 的实例
				BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
				// 将该实例添加到 priorityOrderedPostProcessors 集合中
				priorityOrderedPostProcessors.add(pp);
				// 如果 bean 实现 BeanPostProcessor 接口的实例实现了 MergedBeanDefinitionPostProcessor 接口，
				// 则将 bean 添加到 internalPostProcessors 集合中。
				if (pp instanceof MergedBeanDefinitionPostProcessor) {
					// 将实例 bean 添加到 internalPostProcessors 集合中
					internalPostProcessors.add(pp);
				}
			}
			// 如果 bean 实现了 BeanPostProcessor 接口的实例实现了 Ordered 接口，则将对应的 bean 名称添加到 orderedPostProcessorNames
			// 集合中
			else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
				// 将对应的 bean 名称添加到 orderedPostProcessorNames 集合中
				orderedPostProcessorNames.add(ppName);
			}
			else {
				// 如果不属于实现上述类型接口的 bean 实例，那么就将对应的 bean 名称添加到 nonOrderedPostProcessorNames 集合中
				nonOrderedPostProcessorNames.add(ppName);
			}
		}

		// First, register the BeanPostProcessors that implement PriorityOrdered.
		// 对实现了 PriorityOrdered 接口 bean 实例排序
		sortPostProcessors(priorityOrderedPostProcessors, beanFactory);
		// 将实现了 PriorityOrdered 接口的 bean 实例添加到 beanFactory 中
		registerBeanPostProcessors(beanFactory, priorityOrderedPostProcessors);

		// Next, register the BeanPostProcessors that implement Ordered.
		List<BeanPostProcessor> orderedPostProcessors = new ArrayList<>(orderedPostProcessorNames.size());
		// 注册所有实现了 Ordered 接口的 bean 实例
		for (String ppName : orderedPostProcessorNames) {
			// 根据 bean 名称找到对应的 bean 实例
			BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
			// 添加到 orderedPostProcessors 集合中
			orderedPostProcessors.add(pp);
			// 如果 bean 实例还实现了 MergedBeanDefinitionPostProcessor，那么将该实例添加到 internalPostProcessors 集合中
			if (pp instanceof MergedBeanDefinitionPostProcessor) {
				internalPostProcessors.add(pp);
			}
		}

		// 对实现了 Ordered 的 bean 实例进行排序
		sortPostProcessors(orderedPostProcessors, beanFactory);
		// 将实现了 Ordered 接口的 bean 实例添加到 beanFactory 中
		registerBeanPostProcessors(beanFactory, orderedPostProcessors);

		// Now, register all regular BeanPostProcessors.
		// 注册没有实现 PriorityOrdered 和 Ordered 接口的 bean 实例
		List<BeanPostProcessor> nonOrderedPostProcessors = new ArrayList<>(nonOrderedPostProcessorNames.size());
		for (String ppName : nonOrderedPostProcessorNames) {
			// 根据 bean 名称找到对应的 bean 实例
			BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
			// 将没有实现 PriorityOrdered 和 Ordered 接口的 bean 实例添加到 nonOrderedPostProcessors 集合中
			nonOrderedPostProcessors.add(pp);
			// 如果 bean 实例还实现了 MergedBeanDefinitionPostProcessor，那么将该实例添加到 internalPostProcessors 集合中
			if (pp instanceof MergedBeanDefinitionPostProcessor) {
				internalPostProcessors.add(pp);
			}
		}

		// 将没有实现 PriorityOrdered 和 Ordered 接口的 bean 实例添加到 beanFactory 中
		registerBeanPostProcessors(beanFactory, nonOrderedPostProcessors);

		// Finally, re-register all internal BeanPostProcessors.
		// 将所有实现了 MergedBeanDefinitionPostProcessor 接口的 bean 实例进行排序
		sortPostProcessors(internalPostProcessors, beanFactory);
		// 将所有实现了 MergedBeanDefinitionPostProcessor 接口添加到 beanFactory 中
		registerBeanPostProcessors(beanFactory, internalPostProcessors);

		// Re-register post-processor for detecting inner beans as ApplicationListeners,
		// moving it to the end of the processor chain (for picking up proxies etc).
		// 重新注册用于将内部 bean 检测为 ApplicationListeners 的后处理器，
		// 将其移动到处理器链的末尾（用于获取代理等，所以这个 ApplicationListenerDetector 本身就已经在 beanFactory 内部了，计数不用
		// 再次 +1
		beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(applicationContext));
	}

	private static void sortPostProcessors(List<?> postProcessors, ConfigurableListableBeanFactory beanFactory) {
		// Nothing to sort?
		if (postProcessors.size() <= 1) {
			return;
		}
		Comparator<Object> comparatorToUse = null;
		if (beanFactory instanceof DefaultListableBeanFactory) {
			comparatorToUse = ((DefaultListableBeanFactory) beanFactory).getDependencyComparator();
		}
		if (comparatorToUse == null) {
			comparatorToUse = OrderComparator.INSTANCE;
		}
		postProcessors.sort(comparatorToUse);
	}

	/**
	 * Invoke the given BeanDefinitionRegistryPostProcessor beans.
	 */
	private static void invokeBeanDefinitionRegistryPostProcessors(
			Collection<? extends BeanDefinitionRegistryPostProcessor> postProcessors, BeanDefinitionRegistry registry) {

		for (BeanDefinitionRegistryPostProcessor postProcessor : postProcessors) {
			postProcessor.postProcessBeanDefinitionRegistry(registry);
		}
	}

	/**
	 * Invoke the given BeanFactoryPostProcessor beans.
	 */
	private static void invokeBeanFactoryPostProcessors(
			Collection<? extends BeanFactoryPostProcessor> postProcessors, ConfigurableListableBeanFactory beanFactory) {

		for (BeanFactoryPostProcessor postProcessor : postProcessors) {
			postProcessor.postProcessBeanFactory(beanFactory);
		}
	}

	/**
	 * Register the given BeanPostProcessor beans.
	 */
	private static void registerBeanPostProcessors(
			ConfigurableListableBeanFactory beanFactory, List<BeanPostProcessor> postProcessors) {

		for (BeanPostProcessor postProcessor : postProcessors) {
			beanFactory.addBeanPostProcessor(postProcessor);
		}
	}


	/**
	 * BeanPostProcessor that logs an info message when a bean is created during
	 * BeanPostProcessor instantiation, i.e. when a bean is not eligible for
	 * getting processed by all BeanPostProcessors.
	 */
	private static final class BeanPostProcessorChecker implements BeanPostProcessor {

		private static final Log logger = LogFactory.getLog(BeanPostProcessorChecker.class);

		private final ConfigurableListableBeanFactory beanFactory;

		private final int beanPostProcessorTargetCount;

		public BeanPostProcessorChecker(ConfigurableListableBeanFactory beanFactory, int beanPostProcessorTargetCount) {
			this.beanFactory = beanFactory;
			this.beanPostProcessorTargetCount = beanPostProcessorTargetCount;
		}

		/**
		 * 后置处理器的 before 方法，什么都不做，直接返回对象
		 *
		 * @param bean the new bean instance
		 * @param beanName the name of the bean
		 * @return
		 */
		@Override
		public Object postProcessBeforeInitialization(Object bean, String beanName) {
			return bean;
		}

		/**
		 * 后置处理器的 after 方法，用来判断哪些是不需要检测的 bean
		 *
		 * @param bean the new bean instance
		 * @param beanName the name of the bean
		 * @return
		 */
		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) {
			// 1. BeanPostProcessor 类型不检测
			// 2. ROLE_INFRASTRUCTURE 这种类型的 bean 不检测(Spring 自己的 bean)
			if (!(bean instanceof BeanPostProcessor) && !isInfrastructureBean(beanName) &&
					this.beanFactory.getBeanPostProcessorCount() < this.beanPostProcessorTargetCount) {
				if (logger.isInfoEnabled()) {
					logger.info("Bean '" + beanName + "' of type [" + bean.getClass().getName() +
							"] is not eligible for getting processed by all BeanPostProcessors " +
							"(for example: not eligible for auto-proxying)");
				}
			}
			return bean;
		}

		private boolean isInfrastructureBean(@Nullable String beanName) {
			if (beanName != null && this.beanFactory.containsBeanDefinition(beanName)) {
				BeanDefinition bd = this.beanFactory.getBeanDefinition(beanName);
				return (bd.getRole() == RootBeanDefinition.ROLE_INFRASTRUCTURE);
			}
			return false;
		}
	}

}
