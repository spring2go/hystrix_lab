package io.spring2go.hystrix.command.caching;

import java.util.ArrayList;
import java.util.List;

import io.spring2go.hystrix.domain.Horse;
import io.spring2go.hystrix.service.BettingService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolKey;

/**
 * Get List of Horses in a specific race. Note, calls via this command are cached.
 *
 */
public class CommandGetHorsesInRaceWithCaching extends HystrixCommand<List<Horse>> {

	private final BettingService service;
	private final String raceCourseId;

	/**
	 * CommandGetHorsesInRaceWithCaching.
	 * @param service
	 *            Remote Broker Service
	 * @param raceCourseId
	 *            Id of race course
	 */
	public CommandGetHorsesInRaceWithCaching(BettingService service, String raceCourseId) {
		super(Setter.withGroupKey(
				HystrixCommandGroupKey.Factory.asKey("BettingServiceGroup"))
				.andThreadPoolKey(
						HystrixThreadPoolKey.Factory.asKey("BettingServicePool")));
		
		this.service = service;
		this.raceCourseId = raceCourseId;
	}
	
	@Override
	protected List<Horse> run() {
		return service.getHorsesInRace(raceCourseId);
	}

	@Override
	protected List<Horse> getFallback() {
		// can log here, throw exception or return default
		return new ArrayList<Horse>();
	}

	@Override
	protected String getCacheKey() {
		return raceCourseId;
	}
	
}