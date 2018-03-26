package com.kata.duplicates.api;

import java.util.Collection;

public interface DuplicateCheck {
	Collection<Duplicate> collectCandidates(String path);
	Collection<Duplicate> collectCandidates(String path,EModi modi);
	Collection<Duplicate> checkCandidates(Collection<Duplicate> kandidaten);
}
