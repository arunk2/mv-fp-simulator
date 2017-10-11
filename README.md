# Multi Value Logic Based - Finger Print Unit
Multi Valued - Floating Point unit Simulator. We have implemented a Multi valued Finger Print Simulator with the above mentioned unit and a screenshot of our implementation @ runtime is shown below.

### Multiple-valued logic (MVL) 
Possibility to represent the information with more than two discrete levels. Representing data in a MVL system is more effective than the binary based representation, because the number of interconnections can be significantly reduced. The real advantage of higher-radix arithmetic is fewer arithmetic operations. Especially in bulk signal processing units, they may bring in highly sophisticated but less complicated expressions.

### Why Finger Print Application?
Fingerprint identification is a popular biometric identification technology. It includes fingerprint verification and fingerprint recognition. Both of them have high computations based on complex formulas. They use minutiae, such as end points and bifurcation points, as features. To correctly extract minutiae from fingerprint images becomes an essential step in fingerprint identification. It involves lot of computation for all its operations, makes it an ideal candidate for Multi-valued logic application. 

## Finger Print Unit
- Load Unit: Load FP details (Minutia array) for operation into one of the set of registers
- Store Unit: Store the FP details (Minutia array) back in memory
- Compare Unit: Compare the FP details in register sets and updates the status register with the matching point count. Most of the functional unit impelmentations are complex. Implementation details is out of scope for this report
- Orientation Field Extractor: Finds angle formed by the ridges with horizontal axis 
- FP Area Locator: Separates fingerprint area from the background
- Ridge Extractor: Extarcts Ridges and furrows with filters, as they run parallel to one another forming 2-D sine wave
- Thinner:  Thinning creates a 8-connected thinned ridges of one pixel width
- Minutia Extractor: Spikes and breaks in the thinned ridge map leads to detection of spurious minutiae. Heuristics are applied to post-process the thinned ridge map. If the angle between the branch and the trunk is greater than 70ºand less than 110º, then the branch is removed. If the break in the ridges is less than 15 pixels and no other pixel passes through it, then the break is connected to avoid errors.

## Instruction Processing Unit
Our system has an Instruction processing unit, which has a Instruction queue. This queue holds ‘users’ or ‘developers’ programs done for our coprocessor – which can be configured to be loaded when the system starts. Following section explains the details of the instruction set. IPU processes instructions from the queue one by one.

### Instruction set: 
- Load R1 0000: Loads finger print details (minutia set) starting @ memory 0000 into R1 register
- Store R1 1001: Store finger print details (minutia set) R1 register into memory starting @ 1001
- Compare: Compare finger prints in R1 & R2 and updates the matching count in status register
- Extract c:\images\sample.bmp – Extract the minutia details from bmp file (fully qualified path mentioned in the instruction) and result is available in R1 register
